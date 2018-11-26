#!/usr/bin/env python
# coding: utf-8

# In[ ]:


import numpy as np
import pandas as pd
data=pd.read_csv("dataset1.csv")
data_new=pd.read_csv("dataset1.csv",na_values=['?'])
#data_new=data_new[['age','sex','chest_pain','bp','cholesterol','sugar','ec_result','heart_rate','induced_agina','st_depression','slope','no_vessels','thalassemia','pred_value']]
data_new.dropna(inplace=True)
predictions=data_new['pred_value']
data_new
features_raw = data_new[['age','sex','chest_pain','bp','cholesterol','sugar','ec_result','heart_rate','induced_agina','st_depression','slope','no_vessels','thalassemia']]
from sklearn.model_selection import train_test_split

predict_class = predictions.apply(lambda x: 0 if x == 0 else 1)
np.random.seed(1234)

X_train, X_test, y_train, y_test = train_test_split(features_raw, predict_class, train_size=0.80, random_state=1)


# Show the results of the split
print "Training set has {} samples.".format(X_train.shape[0])
print "Testing set has {} samples.".format(X_test.shape[0])import sklearn
from sklearn import svm

C = 1.0
svc = svm.SVC(kernel='linear',C=C,gamma=2)
svc.fit(X_train, y_train)
from sklearn.metrics import fbeta_score
predictions_test = svc.predict(X_test)
predictions_test

from sklearn.metrics import accuracy_score
print accuracy_score(y_test, predictions_test)

import sklearn
import numpy as np
import pandas as pd
dataframe = pd.read_csv("newdataset.csv")
array = dataframe.values
X = array[:,0:13]
Y = array[:,13]
from sklearn.feature_selection import SelectKBest
from sklearn.feature_selection import chi2
test = SelectKBest(score_func=chi2, k=8)
fit = test.fit(X,Y)
# Summarize scores
np.set_printoptions(precision=3)
print(fit.scores_)
features = fit.transform(X)
# Summarize selected features
print(features[0:10,:])

