#!/bin/bash

cd $PWD
sudo apt-get update 
sudo apt-get install -y python-pip python-dev
pip install --user 'six>-1.9.0' awsebcli --ignore-installed
