<#--noinspection ALL-->
<!DOCTYPE html>
<html lang="zh-cn">

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta content="IE=edge" http-equiv="X-UA-Compatible"/>
    <meta content="width=device-width, initial-scale=1.0" name="viewport"/>
    <title>角色奇遇</title>
    <link href="http://127.0.0.1:2333/resources/css/bootstrap.min.css" rel="stylesheet"/>
    <link href="http://127.0.0.1:2333/resources/css/table.css" rel="stylesheet"/>
</head>

<body>
    <div id="main" style="margin: auto; padding: 0; width: 100%;">
        <div class="card" style="background-image: url('http://127.0.0.1:2333/resources/img/bg.png');">
            <div class="card-header">
                <div class="row">
                    <div class="col">
                        <div class="text-center fs-4">
                            <p><img src="http://127.0.0.1:2333/resources/img/location.png" height="30" width="30" alt="">${server}</p>
                        </div>
                    </div>
                    <div class="col">
                        <div class="text-center fs-4">
                            <p><img src="http://127.0.0.1:2333/resources/img/header.png" height="30" width="30" alt="">${name}</p>
                        </div>
                    </div>
                    <div class="col">
                        <div class="text-center fs-4">
                            <p><img src="http://127.0.0.1:2333/resources/img/cube.png" height="30" width="30" alt="">奇遇统计</p>
                        </div>
                    </div>
                </div>
            </div>
            <div class="card-body">
                <div class="row">
                    <#list level1 as item>
                    <div class="col-4">
                        <div class="card border-0" style="background-color: transparent;width: 18rem;">
                            <img src="http://127.0.0.1:2333/resources/serendipity/${item.event}.png" alt="${item.event}">
                            <div class="card-body text-center fs-5">
                                <p><img src="http://127.0.0.1:2333/resources/img/calendar.png" height="30" width="30" alt="">${item.timeStr}</p>
                                <p>${item.durationStr}</p>
                            </div>
                        </div>
                    </div>
                    </#list>
                    <div class="text-with-hr fs-5">
                        <span>奇遇 ${level1Size} 番 | 珍宠 ${level2Size} 番</span>
                    </div>
                    <#list level2 as item>
                    <div class="col-4">
                        <div class="card border-0" style="background-color: transparent;width: 18rem;">
                            <img src="http://127.0.0.1:2333/resources/serendipity/${item.event}.png" alt="${item.event}">
                            <div class="card-body text-center fs-5">
                                <p><img src="http://127.0.0.1:2333/resources/img/calendar.png" height="30" width="30" alt="">${item.timeStr}</p>
                                <p>${item.durationStr}</p>
                            </div>
                        </div>
                    </div>
                    </#list>
                </div>
            </div>
            <div class="card-footer">
                <h6 class="text-center fs-5">数据来源：www.jx3api.com</h6>
            </div>
        </div>
    </div>
</body>

</html>
