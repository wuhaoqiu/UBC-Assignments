# -*- coding: utf-8 -*-
"""
Created on Wed Aug 15 12:13:46 2018

@author: whq672437089
"""

"""The network uses good practices such as the rectifier activation function for the hidden layer. 
No activation function is used for the output layer because it is a regression problem and we are interested in predicting numerical values directly without transform.
The efficient ADAM optimization algorithm is used and a mean squared error loss function is optimized. 

Usually it's a good practice to apply following formula in order to find out the total number of hidden layers needed.

Nh = Ns/(α∗ (Ni + No))
where

Ni = number of input neurons.
No = number of output neurons.
Ns = number of samples in training data set.
α = an arbitrary scaling factor usually 2-10.
"""



import pandas as pd
import numpy as np
import seaborn as sns
import matplotlib.pyplot as plt
from keras.models import Sequential
from keras.layers import Dense
from sklearn.preprocessing import StandardScaler
from sklearn.model_selection import train_test_split
from sklearn.metrics import mean_squared_error

from numpy.random import seed
seed(1)

def calculate_aare(true_value,predicted_value):
    sum=0
    if len(true_value) == len(predicted_value):
        for true,predicted in zip(true_value,predicted_value):
            sum=sum+abs((true-predicted)/true)
            print(sum)
        return sum*100/len(true_value)
    else:
        return 0
    
def creatNN(num_neuron,num_layer):
    model=Sequential()
    model.add(Dense(num_neuron,input_dim=3,kernel_initializer='normal',activation='relu'))
    for i in range(num_layer):
        model.add(Dense(num_neuron,kernel_initializer='normal',activation='relu'))
    model.add(Dense(1,kernel_initializer='normal'))
    model.compile(loss='mean_squared_error',optimizer='adam')
    return model


def find_parameter(x_train,y_train,x_test,y_test,name,result_dataframe1):
    best_layer=0
    best_neuron=0
    mse=0
    mse_list=[]
    best_predict=0
    for layer in range(3):
        for neuron in range(3,30):
            nn_model=creatNN(neuron,layer)
            nn_model.fit(x_train,y_train,batch_size=len(x_train),epochs=500,verbose=0)
            y_predict=nn_model.predict(x_test)
            mse_list.append(mean_squared_error(y_test,y_predict))
            if layer==0 and neuron==3:
                mse=mean_squared_error(y_test,y_predict)
                best_layer=layer
                best_neuron=neuron
            else:
                if mse > mean_squared_error(y_test,y_predict):
                    mse=mean_squared_error(y_test,y_predict)
                    best_layer=layer
                    best_neuron=neuron
                    best_predict=y_predict
                    
    result_dataframe=result_dataframe1.copy()
    result_dataframe[name]=y_test
    result_dataframe["Predicted"]=y_predict
    print(result_dataframe)
    
    X=range(len(y_test))
    X2=range(len(mse_list))
    plt.subplot(1,2,1)
    plt.scatter(X,y_test,c="y",marker="^",label="True Value")
    plt.plot(y_test,color="blue")
    plt.scatter(X,best_predict, color = 'r', label = 'Predicted Value')
    plt.plot(best_predict,color="orange")
    plt.title('Prediction '+'of'+' '+name)
    plt.legend()
    plt.subplot(1,2,2)
    plt.scatter(X2,mse_list)
    plt.plot(mse_list)
    plt.title("MSE")
    plt.show()
    print("best neron is {}; best hidden layer is {}; min mse is {}".format(best_neuron,best_layer,mse))


data_origin=pd.read_csv("C:/Users/whq672437089/Desktop/engr597/original data/Data_v1.csv")
data_matrix=data_origin.values
attribute=data_matrix[:,0:3]
response=data_matrix[:,3:7]
x_train,x_test,y_train,y_test=train_test_split(attribute,response,test_size=0.3,random_state=0)
result_dataframe1=pd.DataFrame(x_test,columns=list(data_origin.columns.values)[0:3])
scaler=StandardScaler()
x_train=scaler.fit_transform(x_train)
x_test=scaler.transform(x_test)
column_name=list(data_origin.columns.values)[3:7]


for index_column in range(len(y_test.T)):
    name=column_name[index_column]
    find_parameter(x_train,y_train[:,index_column],x_test,y_test[:,index_column],name,result_dataframe1)
    