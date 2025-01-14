#!/bin/bash

echo "--------------- 서버 배포 시작 -----------------"
cd /home/ubuntu/instagram-server
sudo fuser -k -n tcp 8088 || true
nohup java -jar project.jar > ./output.log 2>&1 &
echo "--------------- 서버 배포 끝 -----------------"
