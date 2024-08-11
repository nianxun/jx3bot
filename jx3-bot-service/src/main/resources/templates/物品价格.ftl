<!DOCTYPE html>
<html lang="zh-cn">

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta content="IE=edge" http-equiv="X-UA-Compatible"/>
    <meta content="width=device-width, initial-scale=1.0" name="viewport"/>
    <title>物价</title>
    <link href="http://127.0.0.1:2333/resources/css/bootstrap.min.css" rel="stylesheet"/>
</head>

<body>
<div id="main" style="margin: 0; padding: 0; width: 100%;">
    <div class="card" style="background-image: url('http://127.0.0.1:2333/resources/img/bg.png');">
        <div class="card-header">
            <p class="text-center fs-2">${name}(${subalias})</p>
        </div>
        <div class="card-body">
            <div class="row">
                <img src="${view}" alt=""/>
            </div>
            <div class="row">
                <div class="alert alert-secondary text-center">
                    <p class="fs-2">${desc}</p>
                </div>
            </div>
            <table class="table table-bordered">
                <#assign scores = {"1" : "出售","2" : "收购","3" : "想出","4" : "想收","5" : "成交","6" : "正出","7" : "公示"}>
                <tr>
                    <td>
                        <table class="table table-striped table-borderless h5">
                            <thead>
                            <p class="text-center fs-3"><strong>电信区</strong></p>
                            </thead>
                            <tbody>
                            <#list data[0] as one>
                                <tr>
                                    <td class='text-center text-warning'>${one.date}</td>
                                    <td class='text-center'>${one.server}</td>
                                    <td class='text-center text-primary'>${one.value}</td>
                                    <td class='text-center'>
                                        ${scores[one.sales?string]}
                                    </td>
                                </tr>
                            </#list>
                            </tbody>
                        </table>
                    </td>
                    <td>
                        <table class="table table-striped table-borderless h5">
                            <thead>
                            <p class="text-center fs-3"><strong>双线区</strong></p>
                            </thead>
                            <tbody>
                            <#list data[1] as one>
                                <tr>
                                    <td class='text-center text-warning'>${one.date}</td>
                                    <td class='text-center'>${one.server}</td>
                                    <td class='text-center text-primary'>${one.value}</td>
                                    <td class='text-center'>
                                        ${scores[one.sales?string]}
                                    </td>
                                </tr>
                            </#list>
                            </tbody>
                        </table>
                    </td>
                </tr>
                <tr>
                    <td>
                        <table class="table table-striped table-borderless h5">
                            <thead>
                            <p class="text-center fs-3"><strong>无界区</strong></p>
                            </thead>
                            <tbody>
                            <#list data[2] as one>
                                <tr>
                                    <td class='text-center text-warning'>${one.date}</td>
                                    <td class='text-center'>${one.server}</td>
                                    <td class='text-center text-primary'>${one.value}</td>
                                    <td class='text-center'>
                                        ${scores[one.sales?string]}
                                    </td>
                                </tr>
                            </#list>
                            </tbody>
                        </table>
                    </td>
                    <td>
                        <table class="table table-striped table-borderless h5">
                            <thead>
                            <p class="text-center fs-3"><strong>万宝楼·正售</strong></p>
                            </thead>
                            <tbody>
                            <#list data[4] as one>
                                <tr>
                                    <td class='text-center text-warning'>${one.date}</td>
                                    <td class='text-center'>${one.server}</td>
                                    <td class='text-center text-primary'>${one.value}</td>
                                    <td class='text-center'>
                                        ${scores[one.sales?string]}
                                    </td>
                                </tr>
                            </#list>
                            </tbody>
                        </table>
                    </td>
                </tr>
            </table>
        </div>
        <div class="card-footer">
            <h6 class="text-center">数据来源：www.jx3api.com</h6>
        </div>
    </div>
</div>
</body>

</html>
