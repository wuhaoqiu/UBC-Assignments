# -*- coding: utf-8 -*-
"""
Created on Thu Aug 16 16:03:37 2018

@author: whq672437089
"""
from numpy.random import seed
seed(1)

import pandas as pd
import numpy as np
import seaborn as sns
import matplotlib.pyplot as plt
from sklearn.preprocessing import StandardScaler
from sklearn.model_selection import train_test_split
from sklearn.metrics import mean_squared_error
from sklearn.ensemble import RandomForestRegressor

def find_parameter(x_train,y_train,x_test,y_test,name,result_dataframe1):
    best_num_trees=0
    best_num_features=0
    mse=0
    mse_list=[]
    best_predict=0
    for num_features in range(1,4):
        for num_trees in range(100,2000,100):
            rf = RandomForestRegressor(n_estimators=num_trees,max_features=num_features, oob_score=True,random_state=0)
            rf.fit(x_train, y_train)
            y_predict=rf.predict(x_test)
            mse_list.append(mean_squared_error(y_test,y_predict))
            if num_features==1 and num_trees==100:
                mse=mean_squared_error(y_test,y_predict)
                best_num_trees=num_features
                best_num_features=num_trees
            else:
                if mse > mean_squared_error(y_test,y_predict):
                    mse=mean_squared_error(y_test,y_predict)
                    best_num_trees=num_trees
                    best_num_features=num_features
                    best_predict=y_predict
    
    result_dataframe=result_dataframe1.copy()
    result_dataframe[name]=y_test[:,index_column]
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
    print("best number of features is {}; best num of trees is {}; min mse is {}".format(best_num_features,best_num_trees,mse))



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
    
    find_parameter(x_train,y_train[:,index_column],x_test,y_test[:,index_column],name,result_dataframe1)
    
