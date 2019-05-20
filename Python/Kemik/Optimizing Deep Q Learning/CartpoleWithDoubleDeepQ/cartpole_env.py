
    
# -*- coding: utf-8 -*-
"""
Created on Tue Oct 30 17:55:56 2018
@author: @author: Artem Oppermann
"""
from itertools import product

from mpl_toolkits.mplot3d import Axes3D
##
import seaborn as sns
import numpy as np
import tensorflow as tf
import gym
from sklearn.decomposition import PCA
from ddqn_model import DDQN
from exp_replay import ExperienceReplay
import matplotlib.pyplot as plt
import matplotlib.pyplot as plt2
from sklearn.preprocessing import StandardScaler
class CartPole:
    """
    This class build the openAI Gym Environments and runs episodes of the environment.
    
    :param FLAGS: TensorFlow flags which contain the values for hyperparameters
    """
    
    def __init__(self, FLAGS):
        
        self.FLAGS=FLAGS
        self.env = gym.make('CartPole-v1')
        self.state_size = len(self.env.observation_space.sample())
        self.num_episodes=500
        
        # Build the experience replay class
        self.exp_replay = ExperienceReplay()
        
        # Build the Target-Network
        target_network=DDQN(scope='target', env=self.env,target_network=None, flags=FLAGS, exp_replay=None)
        # Build the Q-Network
        self.q_network=DDQN(scope='q_network',env=self.env,target_network=target_network, flags=FLAGS, exp_replay=self.exp_replay)

        init = tf.global_variables_initializer()
        session = tf.InteractiveSession()
        session.run(init)
        
        self.q_network.set_session(session)
        target_network.set_session(session)
        
          
    def playEpisode(self,eps):
        """Play one single episode of the environmet. 
        :param eps: value of probability epsilon
        
        """
        # Init and reshape first state
        state=self.env.reset()
        state=state.reshape(1,self.state_size)
        sorunlar = []
        num_iter=0
        done=False
        total_reward=0
        stateler= []
        actionlar = []
        i=0
        while not done:
            # Get an action
            action,i=self.q_network.get_action(state,eps,i)
            prev_state=state
            # Receive the next sate, reward and done
            actionlar.append(action)
            state, reward, done, _ = self.env.step(action)
            state=state.reshape(1,self.state_size)
            sorunlar.append(reward)
            stateler.append(state)
            ############# Uncomment this line if you want to render the environment###########
            #self.env.render(mode='rgb_array')
            total_reward=total_reward+reward
            if done:
                reward=-100
            # Add <s,a,r,s',done> tuple to the experience replay memory
            self.exp_replay.addExperience(prev_state, action, reward, state, done)
            self.q_network.train_q_network()
            
            num_iter+=1

            if (num_iter% self.FLAGS.num_iter_update) == 0:
                self.q_network.update_target_parameter()
        print("Total Reward :",total_reward,"Stateler \n")
        print(stateler)
        #for iterator in stateler :
        #    print(iterator,"\n")
        if (total_reward < 200 ):
            print("###")
        print("Epsilona girme sayisi :",i,"\n********************************************\n")
        print(actionlar)
        return total_reward,stateler,actionlar
            

    def run(self):
        """ Main loop for the running of the episodes. """
        reward_List = []
        totalrewards = np.empty(self.num_episodes+1)
        n_steps=10
        AllActionlar = []
        statelerVeActionlar = []
        for n in range(0, self.num_episodes+1):

            eps = 1.0/np.sqrt(n+1)
            print(n,". Episode İcin ")
            total_reward,stateler,actionlar=self.playEpisode(eps)
            AllActionlar.append(actionlar)
            reward_List.append(total_reward)
            totalrewards[n]=total_reward
            scaler = StandardScaler()
            print(stateler[0])
            print( len(stateler[0][0]))
            for i in range( 0,len(stateler)):
                # Nedense 1 fazla boyutta tuyurodu stateleri o boyutu kaldırdım 2 boyutlu bir matrix yaptım
                tmp = []
                tmp.append(stateler[i][0][0])
                tmp.append(stateler[i][0][1])
                tmp.append(stateler[i][0][2])
                tmp.append(stateler[i][0][3])
                print(tmp)
                stateler[i]=tmp
            scaler.fit(stateler) #Mean ve standart sapma hesaplıyor
            principalComeponents=(scaler.transform(stateler)) # Meani 0 olacak şekilde ayarlıyor
            pca = PCA(n_components=2) #pca ilklendirme
            principalComeponents = pca.fit_transform(principalComeponents) #boyutu indirigiyor
            statelerVeActionlar.append(principalComeponents)
           # if n>0 and n%n_steps==0:
            #    print("episodes: %i, avg_reward (last: %i episodes): %.2f, eps: %.2f" %(n, n_steps, totalrewards[max(0, n-n_steps):(n+1)].mean(), eps))
        #
        plt.plot(reward_List) # 1 satırda 2 tane plot oluştur
        plt.show()
        fig = plt.figure(figsize=(30,25))
        ax = fig.add_subplot(111, projection='3d')
        index =np.arange(0,self.num_episodes+1,1)
        print("Buraya dikkat")
        print(statelerVeActionlar)
        print ( "Aranan -****************************** ")
        X = []
        Y = []
        X1 = []
        Y1 = []
        X2 = []
        Y2 = []
        indexOnes =[]
        indexZeros = []
        min = 9000000
        max = 0
        X3 = []
        Y3 = []
        X4 = []
        Y4 = []
        indexOfMins=[]
        indexOfMins2 = []
        for i in range(int(len(totalrewards)*24/25), len(totalrewards)):
            if (totalrewards[i] < min):
                min = totalrewards[i]
                minIndex = i
            if (totalrewards[i] > max):
                max = totalrewards[i]
                maxIndex = i
        indexforcolor = 0
        for i in range ( 0,len(statelerVeActionlar)):
            for j in range (0 , len(statelerVeActionlar[i])):
                X.append(statelerVeActionlar[i][j][0])
                Y.append(statelerVeActionlar[i][j][1])
                if AllActionlar[i][j] == 1:
                    Y1.append(Y[indexforcolor])
                    X1.append(X[indexforcolor])
                    indexOnes.append(i)
                    if (i == minIndex or i == maxIndex):
                        Y3.append(Y[indexforcolor])
                        X3.append(X[indexforcolor])
                        indexOfMins.append(i)
                else:
                    Y2.append(Y[indexforcolor])
                    X2.append(X[indexforcolor])
                    indexZeros.append(i)
                    if (i == minIndex or i == maxIndex):
                        Y4.append(Y[indexforcolor])
                        X4.append(X[indexforcolor])
                        indexOfMins2.append(i)
                indexforcolor += 1
        print("Printing  X" , X)
        print("Printing  Y" , Y)
        print(len(X))
        print(len(Y))
        print(len(index))
        print(len(AllActionlar))
        print(AllActionlar)

       # ax.set_axis_bgcolor('black')
       #  ax.xaxis.label.set_color('white')
       #  ax.yaxis.label.set_color('white')
       #  ax.zaxis.label.set_color('white')
       #  ax.tick_params(axis='x', colors='white')
       #  ax.tick_params(axis='y', colors='white')
       #  ax.tick_params(axis='z', colors='white')
        ax.scatter(indexOnes, X1, Y1, marker='o',color='red',s=5)
        ax.scatter(indexZeros , X2 , Y2 , marker ='o',color = 'green',s=5)
        ax.set_xlabel("Episode Sayisi")
        ax.set_ylabel("PCA ile indirilen 2 boyuttan birincisi")
        ax.set_zlabel("PCA ile indirilen 2 boyuttan ikincisi")
        yazilacakyazi="Min Reward Degeri alan epsodeun indisi : "+str(minIndex)
        yazilacakyazi+="  Max Reward Degeri alan epsodeun indisi : "+str(maxIndex)
        ax.text2D(0.05, 0.95, yazilacakyazi, transform=ax.transAxes)
        fig2 = plt.figure()
        ax2 = fig2.add_subplot(111, projection='3d')
        print(minIndex)
        print(maxIndex)
        print(len(indexOfMins))
        print(len(X3))
        indexOfLastQuarter=[]
        indexOfLastQuarter2 = []
        X5=[]
        X6=[]
        Y5=[]
        Y6=[]
        print(indexOnes)
        for i in range(0,len(indexOnes)):
            print("Deget" ,indexOnes[i])
            if(indexOnes[i]>(int)(self.num_episodes*24/25)):
                indexOfLastQuarter.append(indexOnes[i])
                X5.append(X1[i])
                Y5.append(Y1[i])
        for i in range(0,len(indexZeros)):
            if(indexZeros[i]>(int)(self.num_episodes*24/25)):
                indexOfLastQuarter2.append(indexZeros[i])
                X6.append(X2[i])
                Y6.append(Y2[i])
        ax2.scatter(indexOfMins,X3,Y3,marker='o',color='red')
        ax2.scatter(indexOfMins2,X4,Y4,marker='o',color='green')
        fig3 = plt.figure()
        ax3 = fig3.add_subplot(111, projection='3d')
        ax3.scatter(indexOfLastQuarter, X5, Y5, marker='o', color='red')
        ax3.scatter(indexOfLastQuarter2, X6, Y6, marker='o', color='green')

        plt.show()

