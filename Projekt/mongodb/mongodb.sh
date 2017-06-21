#!/bin/bash
# script file for starting, stoping, restarting mongodb or displaying stats
# (linux script so it may not work on other systems but you can see how it should be set up


#mongodb root directory
dbrootdir="/opt/mongodb/mongodb-linux-x86_64-ubuntu1604-3.4.5/"
#location of your database
dbdir=$dbrootdir"data/db"
#location of config file
#dbconffile=$dbrootdir"data/mongodb.conf"
dbconffile=/opt/mongodb/mongodb-linux-x86_64-ubuntu1604-3.4.5/data/mongodb.conf

echo $dbrootdir
echo $dbdir
echo $dbconffile

if [ "$1" == "start" ]
then
	$dbrootdir"bin/mongod" -f $dbconffile 
fi
if [ "$1" == "stop" ]
then
	$dbrootdir"bin/mongod" -f $dbconffile --shutdown
fi
if [ "$1" == "restart" ]
then
	$dbrootdir"bin/mongod" -f $dbconffile --shutdown
	$dbrootdir"bin/mongod" -f $dbconffile 
fi
if [ "$1" == "stat" ]
then
	$dbrootdir"bin/mongostat"
fi
