#!/usr/local/bin/expect -f
#
# Auto pack, scp, restart app by expect.
#
# Use example:
#   ./pack_dev.sh developer 192.140.12.2 pwd /java/workspace-idea-soundbus/bet-game-service /java/workspace-idea-soundbus/bet-game-service/bet-game/target/bet-game.jar /home/developer/sunbar/bet-game-service/bet-game-$(date +'%Y%m%d').jar bet-game /home/developer/sunbar/bet-game-service
#
# Init env(mac os):
#   brew install expect
#
# Created by suzhida on 2017-08-06 14:47:11

set service_user [lindex $argv 0]
set service_ip [lindex $argv 1]
set service_pwd [lindex $argv 2]

set pack_path [lindex $argv 3]
set scp_source_path [lindex $argv 4]
set scp_to_path [lindex $argv 5]
set app_name [lindex $argv 6]
set service_app_path [lindex $argv 7]

set timeout -1
# bash shell
spawn $env(SHELL)
match_max 100000
expect -exact ""

# pack
send -- "cd $pack_path\r"
expect -exact "cd $pack_path"
send -- "mvn clean install -Dskip.unit.tests=true\r"
expect -exact "mvn clean install -Dskip.unit.tests=true"

# scp
send -- "scp $scp_source_path $service_user@$service_ip:$scp_to_path\r"
expect -exact "$service_user@$service_ip's password: "
send -- "$service_pwd\r"
expect -exact ""

# restart app
send -- "ssh -p 22 $service_user@$service_ip\r"
expect -exact "$service_user@$service_ip's password: "
send -- "$service_pwd\r"
expect -exact ""
send -- "cd $service_app_path\r"
expect -exact ""
send -- "ln -sf $app_name-\$(date +'%Y%m%d').jar $app_name-current.jar\r"
expect -exact ""
send -- "./boot.sh\r"
expect -exact ""
send -- "tailf log/spring.log\r"
expect -exact ""

# done
interact