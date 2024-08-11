<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <link rel="stylesheet" type="text/css" href="http://127.0.0.1:2333/resources/css/pvp/750.5ffd3309.css">
    <style>
        .map-title {
            position: absolute;
            top: 10px;
            left: 55%;
            transform: translateX(-50%);
            font-size: 2vw;
            color: rgb(247,234,188);
            padding: 5px;
            border-radius: 5px;
            white-space: nowrap;
        }

        .map-time {
            position: absolute;
            top: 50px;
            left: 55%;
            transform: translateX(-50%);
            font-size: 1vw;
            color: rgb(247,234,188);
            padding: 5px;
            border-radius: 5px;
            white-space: nowrap;
        }

        .footer {
            position: absolute;
            bottom: 0;
            left: 55%;
            transform: translateX(-50%);
            font-size: 1vw;
            color: rgb(247,234,188);
            padding: 5px;
            border-radius: 5px;
            white-space: nowrap;
        }

        
        @font-face {
            font-family: Harmony;
            src: url("font/custom.ttf");
        }

        body {
            font-family: Harmony, sans-serif;
        }
    </style>
    <meta charset="UTF-8">
    <title></title>
</head>

<body>
    <div class="m-sandbox-map">
        <div class="m-sandboxMap">
            <div class="map-title">${server}</div>
            <div class="map-time">${update}</div>
            <!--阵营防线-->
            <div class="u-mapLine"></div>

            <!--浩气和恶人本部-->
            <div>
                <img class="u-pic" src="http://127.0.0.1:2333/resources/img/pvp/e27.png" style="left: 72px; top: 10px;" alt="" />
                <img class="u-icon" src="http://127.0.0.1:2333/resources/img/pvp/erengu.png" style="left: 149px; top: 49px; cursor: default; " alt="" />
                <div class="u-name" style="left: 234px; top: 39px;">恶人谷</div>
            </div>
            <div>
                <img class="u-pic" src="http://127.0.0.1:2333/resources/img/pvp/h25.png" style="left: 683px; top: 523px;" alt="" />
                <img class="u-icon" src="http://127.0.0.1:2333/resources/img/pvp/haoqimeng.png" style="left: 762px; top: 622px; cursor: default;" alt="" />
                <div class="u-name" style="left: 847px; top: 612px;">浩气盟</div>
            </div>

            <!--据点地图-->
            <div>
                <img class="u-pic" src="http://127.0.0.1:2333/resources/img/pvp/${map["秋雨堡"]}92.png" style="left: 607px; top: 259px;" alt="" />
                <div class="u-name" style="left: 743px; top: 274px;">红莲岗</div>
            </div>
            <div>
                <img class="u-pic" src="http://127.0.0.1:2333/resources/img/pvp/${map["秋雨堡"]}91.png" style="left: 623px; top: 318px;" alt="" />
                <div class="u-name" style="left: 719px; top: 360px;">秋雨堡</div>
            </div>
            <div>
                <img class="u-pic" src="http://127.0.0.1:2333/resources/img/pvp/${map["金门关"]}131.png" style="left: 637px; top: 123px;" alt="" />
                <div class="u-name" style="left: 729px; top: 202px;">金门关</div>
            </div>
            <div><img class="u-pic" src="http://127.0.0.1:2333/resources/img/pvp/${map["青云坞"]}132.png" style="left: 733px; top: 144px;" alt="" />
                <div class="u-name" style="left: 813px; top: 168px;">青云坞</div>
            </div>
            <div><img class="u-pic" src="http://127.0.0.1:2333/resources/img/pvp/${map["盘龙坞"]}211.png" style="left: 502px; top: 476px;" alt="" />
                <div class="u-name" style="left: 623px; top: 526px;">盘龙坞</div>
            </div>
            <div><img class="u-pic" src="http://127.0.0.1:2333/resources/img/pvp/${map["逐鹿坪"]}212.png" style="left: 573px; top: 391px;" alt="" />
                <div class="u-name" style="left: 667px; top: 424px;">逐鹿坪</div>
            </div>
            <div><img class="u-pic" src="http://127.0.0.1:2333/resources/img/pvp/${map["武王城"]}221.png" style="left: 562px; top: 430px;" alt="" />
                <div class="u-name" style="left: 766px; top: 550px;">武王城</div>
            </div>
            <div><img class="u-pic" src="http://127.0.0.1:2333/resources/img/pvp/${map["龙门镇"]}231.png" style="left: 395px; top: 117px;" alt="" />
                <div class="u-name" style="left: 495px; top: 152px;">龙门镇</div>
            </div>
            <div><img class="u-pic" src="http://127.0.0.1:2333/resources/img/pvp/${map["飞沙关"]}232.png" style="left: 362px; top: 136px;" alt="" />
                <div class="u-name" style="left: 415px; top: 132px;">飞沙关</div>
            </div>
            <div><img class="u-pic" src="http://127.0.0.1:2333/resources/img/pvp/${map["凛风堡"]}301.png" style="left: 149px; top: 65px;" alt="" />
                <div class="u-name" style="left: 344px; top: 122px;">凛风堡</div>
            </div>
            <div><img class="u-pic" src="http://127.0.0.1:2333/resources/img/pvp/${map["不空关"]}351.png" style="left: 392px; top: 368px;" alt="" />
                <div class="u-name" style="left: 525px; top: 382px;">不空关</div>
            </div>
            <div><img class="u-pic" src="http://127.0.0.1:2333/resources/img/pvp/${map["激流坞"]}352.png" style="left: 432px; top: 431px;" alt="" />
                <div class="u-name" style="left: 503px; top: 456px;">激流坞</div>
            </div>
            <div><img class="u-pic" src="http://127.0.0.1:2333/resources/img/pvp/${map["卧龙坡"]}1001.png" style="left: 226px; top: 476px;" alt="" />
                <div class="u-name" style="left: 365px; top: 514px;">卧龙坡</div>
            </div>
            <div><img class="u-pic" src="http://127.0.0.1:2333/resources/img/pvp/${map["日月崖"]}1002.png" style="left: 257px; top: 356px;" alt="" />
                <div class="u-name" style="left: 377px; top: 418px;">日月崖</div>
            </div>
            <div><img class="u-pic" src="http://127.0.0.1:2333/resources/img/pvp/${map["霜戈堡"]}1011.png" style="left: 50px; top: 602px;" alt="" />
                <div class="u-name" style="left: 147px; top: 630px;">霜戈堡</div>
            </div>
            <div><img class="u-pic" src="http://127.0.0.1:2333/resources/img/pvp/${map["澜沧城"]}1012.png" style="left: 123px; top: 626px;" alt="" />
                <div class="u-name" style="left: 215px; top: 708px;">澜沧城</div>
            </div>
            <div><img class="u-pic" src="http://127.0.0.1:2333/resources/img/pvp/${map["神池岭"]}1031.png" style="left: 109px; top: 206px;" alt="" />
                <div class="u-name" style="left: 174px; top: 224px;">神池岭</div>
            </div>
            <div><img class="u-pic" src="http://127.0.0.1:2333/resources/img/pvp/${map["烈日岗"]}1032.png" style="left: 12px; top: 271px;" alt="" />
                <div class="u-name" style="left: 133px; top: 338px;">烈日岗</div>
            </div>
            <div><img class="u-pic" src="http://127.0.0.1:2333/resources/img/pvp/${map["惊虬谷"]}1041.png" style="left: 11px; top: 386px;" alt="" />
                <div class="u-name" style="left: 177px; top: 434px;">惊虬谷</div>
            </div>
            <div><img class="u-pic" src="http://127.0.0.1:2333/resources/img/pvp/${map["凤鸣堡"]}1042.png" style="left: 23px; top: 494px;" alt="" />
                <div class="u-name" style="left: 107px; top: 512px;">凤鸣堡</div>
            </div>
            <div><img class="u-pic" src="http://127.0.0.1:2333/resources/img/pvp/${map["大理山城"]}1051.png" style="left: 304px; top: 606px;" alt="" />
                <div class="u-name" style="left: 381px; top: 612px;">大理山城</div>
            </div>
            <div><img class="u-pic" src="http://127.0.0.1:2333/resources/img/pvp/${map["千岩关"]}1052.png" style="left: 305px; top: 581px;" alt="" />
                <div class="u-name" style="left: 435px; top: 724px;">千岩关</div>
            </div>
            <div><img class="u-pic" src="http://127.0.0.1:2333/resources/img/pvp/${map["啖杏林"]}1391.png" style="left: 502px; top: 152px;" alt="" />
                <div class="u-name" style="left: 561px; top: 162px;">啖杏林</div>
            </div>
            <div><img class="u-pic" src="http://127.0.0.1:2333/resources/img/pvp/${map["枫湖寨"]}1392.png" style="left: 496px; top: 203px;" alt="" />
                <div class="u-name" style="left: 623px; top: 230px;">枫湖寨</div>
            </div>
            <div><img class="u-pic" src="http://127.0.0.1:2333/resources/img/pvp/${map["世外坡"]}1531.png" style="left: 234px; top: 233px;" alt="" />
                <div class="u-name" style="left: 345px; top: 298px;">世外坡</div>
            </div>
            <div><img class="u-pic" src="http://127.0.0.1:2333/resources/img/pvp/${map["扶风郡"]}1532.png" style="left: 295px; top: 187px;" alt="" />
                <div class="u-name" style="left: 371px; top: 212px;">扶风郡</div>
            </div>

            <!--据点图标-->
            <div class="u-icon" style="left: 674px; top: 370px;">
                <div><img class="i-icon" src="http://127.0.0.1:2333/resources/img/pvp/${map["秋雨堡"]}01.png" alt="" /></div>
            </div>
            <div class="u-icon" style="left: 698px; top: 284px;">
                <div><img class="i-icon" src="http://127.0.0.1:2333/resources/img/pvp/${map["红莲岗"]}02.png" alt="" /></div>
            </div>
            <div class="u-icon" style="left: 684px; top: 212px;">
                <div><img class="i-icon" src="http://127.0.0.1:2333/resources/img/pvp/${map["金门关"]}04.png" alt="" /></div>
            </div>
            <div class="u-icon" style="left: 768px; top: 178px;">
                <div><img class="i-icon" src="http://127.0.0.1:2333/resources/img/pvp/${map["青云坞"]}01.png" alt="" /></div>
            </div>
            <div class="u-icon" style="left: 578px; top: 536px;">
                <div><img class="i-icon" src="http://127.0.0.1:2333/resources/img/pvp/${map["盘龙坞"]}05.png" alt="" /></div>
            </div>
            <div class="u-icon" style="left: 622px; top: 434px;">
                <div><img class="i-icon" src="http://127.0.0.1:2333/resources/img/pvp/${map["逐鹿坪"]}03.png" alt="" /></div>
            </div>
            <div class="u-icon" style="left: 706px; top: 560px;">
                <div><img class="i-icon" src="http://127.0.0.1:2333/resources/img/pvp/${map["武王城"]}_wuwangcheng.png" alt="" /></div>
            </div>
            <div class="u-icon" style="left: 450px; top: 162px;">
                <div><img class="i-icon" src="http://127.0.0.1:2333/resources/img/pvp/${map["龙门镇"]}02.png" alt="" /></div>
            </div>
            <div class="u-icon" style="left: 370px; top: 142px;">
                <div><img class="i-icon" src="http://127.0.0.1:2333/resources/img/pvp/${map["飞沙关"]}01.png" alt="" /></div>
            </div>
            <div class="u-icon" style="left: 284px; top: 132px;">
                <div><img class="i-icon" src="http://127.0.0.1:2333/resources/img/pvp/${map["凛风堡"]}_lingfengbao.png" alt="" /></div>
            </div>
            <div class="u-icon" style="left: 480px; top: 392px;">
                <div><img class="i-icon" src="http://127.0.0.1:2333/resources/img/pvp/${map["不空关"]}04.png" alt="" /></div>
            </div>
            <div class="u-icon" style="left: 458px; top: 466px;">
                <div><img class="i-icon" src="http://127.0.0.1:2333/resources/img/pvp/${map["激流坞"]}01.png" alt="" /></div>
            </div>
            <div class="u-icon" style="left: 320px; top: 524px;">
                <div><img class="i-icon" src="http://127.0.0.1:2333/resources/img/pvp/${map["卧龙坡"]}03.png" alt="" /></div>
            </div>
            <div class="u-icon" style="left: 332px; top: 428px;">
                <div><img class="i-icon" src="http://127.0.0.1:2333/resources/img/pvp/${map["日月崖"]}02.png" alt="" /></div>
            </div>
            <div class="u-icon" style="left: 102px; top: 640px;">
                <div><img class="i-icon" src="http://127.0.0.1:2333/resources/img/pvp/${map["霜戈堡"]}01.png" alt="" /></div>
            </div>
            <div class="u-icon" style="left: 170px; top: 718px;">
                <div><img class="i-icon" src="http://127.0.0.1:2333/resources/img/pvp/${map["澜沧城"]}03.png" alt="" /></div>
            </div>
            <div class="u-icon" style="left: 129px; top: 234px;">
                <div><img class="i-icon" src="http://127.0.0.1:2333/resources/img/pvp/${map["神池岭"]}02.png" alt="" /></div>
            </div>
            <div class="u-icon" style="left: 88px; top: 348px;">
                <div><img class="i-icon" src="http://127.0.0.1:2333/resources/img/pvp/${map["烈日岗"]}01.png" alt="" /></div>
            </div>
            <div class="u-icon" style="left: 132px; top: 444px;">
                <div><img class="i-icon" src="http://127.0.0.1:2333/resources/img/pvp/${map["惊虬谷"]}01.png" alt="" /></div>
            </div>
            <div class="u-icon" style="left: 62px; top: 522px;">
                <div><img class="i-icon" src="http://127.0.0.1:2333/resources/img/pvp/${map["凤鸣堡"]}04.png" alt="" /></div>
            </div>
            <div class="u-icon" style="left: 336px; top: 622px;">
                <div><img class="i-icon" src="http://127.0.0.1:2333/resources/img/pvp/${map["大理山城"]}04.png" alt="" /></div>
            </div>
            <div class="u-icon" style="left: 390px; top: 734px;">
                <div><img class="i-icon" src="http://127.0.0.1:2333/resources/img/pvp/${map["千岩关"]}01.png" alt="" /></div>
            </div>
            <div class="u-icon" style="left: 516px; top: 172px;">
                <div><img class="i-icon" src="http://127.0.0.1:2333/resources/img/pvp/${map["啖杏林"]}01.png" alt="" /></div>
            </div>
            <div class="u-icon" style="left: 578px; top: 240px;">
                <div><img class="i-icon" src="http://127.0.0.1:2333/resources/img/pvp/${map["枫湖寨"]}03.png" alt="" /></div>
            </div>
            <div class="u-icon" style="left: 300px; top: 308px;">
                <div><img class="i-icon" src="http://127.0.0.1:2333/resources/img/pvp/${map["世外坡"]}05.png" alt="" /></div>
            </div>
            <div class="u-icon" style="left: 326px; top: 222px;">
                <div><img class="i-icon" src="http://127.0.0.1:2333/resources/img/pvp/${map["扶风郡"]}01.png" alt="" /></div>
            </div>
            <div class="footer">参考于 Inkar Suki</div>
        </div>
    </div>
</body>

</html>