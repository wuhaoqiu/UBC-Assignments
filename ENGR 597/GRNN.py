# -*- coding: utf-8 -*-
"""
Created on Thu Aug 16 16:59:25 2018

@author: whq672437089
"""


from neupy import algorithms
import pandas as pd
import numpy as np
import seaborn as sns
import matplotlib.pyplot as plt
from sklearn.preprocessing import StandardScaler
from sklearn.model_selection import train_test_split
from sklearn.metrics import mean_squared_error

from numpy.random import seed
seed(1)

data_origin=pd.read_csv("C:/Users/whq672437089/Desktop/engr597/original data/Data_v1.csv")
data_origin.corr()
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
    nw=algorithms.GRNN(std=0.1,verbose=False)
    nw.train(x_train,y_train[:,index_column])
    y_predict=nw.predict(x_test)
    
    result_dataframe=result_dataframe1.copy()
    result_dataframe[name]=y_test[:,index_column]
    result_dataframe["Predicted"]=y_predict
    print(result_dataframe)
    print("MSE is {}".format(mean_squared_error(y_test[:,index_column],y_predict)))
    X=range(len(y_test))
    plt.scatter(X,y_test[:,index_column],c="y",marker="^",label="True Value")
    plt.plot(y_test[:,index_column],color="blue")
    plt.scatter(X,y_predict, color = 'r', label = 'Predicted Value')
    plt.plot(y_predict,color="orange")
    plt.title('Prediction '+'of'+' '+name)
    plt.legend()
    plt.show()
    
