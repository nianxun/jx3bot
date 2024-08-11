<#--noinspection ALL-->
<!DOCTYPE html>
<html lang="zh-cn">

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta content="IE=edge" http-equiv="X-UA-Compatible"/>
    <meta content="width=device-width, initial-scale=1.0" name="viewport"/>
    <title>角色装备</title>
    <link href="http://127.0.0.1:2333/resources/css/bootstrap.min.css" rel="stylesheet"/>
    <link href="http://127.0.0.1:2333/resources/css/table.css" rel="stylesheet"/>
</head>

<body>
<div id="main" style="margin: 0; padding: 0; width: 100%;">
    <div class="card" style="background-image: url('http://127.0.0.1:2333/resources/img/bg.png');">
        <div class="card-header">
            <div style="text-align:center; margin:10px">
                <h2><img src="http://127.0.0.1:2333/resources/sect/${kungfuName}.png" height="70" width="70" alt=""/><strong> ${serverName} •
                        ${roleName}</strong></h2>
            </div>
        </div>
        <div class="card-body">
            <!--角色属性-->
            <#if panelList.panel??>
                <hr>
                <div class="row" style="padding: 20px">
                    <#list panelList.panel as item>
                        <div class="col-4">
                            <h3><strong>${item.name!}：</strong>${item.value!}</h3>
                        </div>
                    </#list>
                </div>
                <h2 class="tittle-with-hr text-center">
                    <span><strong>装备分数：${panelList.score!}</strong></span>
                </h2>
            </#if>
            <!--角色装备-->
            <div class="row">
                <#list equipList as item>
                    <div class="row">
                        <div class="col-1">
                            <div style="border: 3px solid ${item.color!}; margin: 0 -10px 0 0;">
                                <img src="${item.icon!}" width="70" height="70" alt=""/>
                            </div>
                        </div>
                        <div class="col-4">
                            <p style="margin:1px;font-size: 25px;"><strong
                                        style="color: ${item.color!};">${item.name!}</strong>(${item.quality!})</p>
                            <p style="margin:1px;">
                                <#list item.modifyType! as modifyType>
                                    ${modifyType.name}
                                </#list>
                            </p>

                            <p style="margin:1px;color: darkgray;">${item.source!}</p>
                        </div>
                        <div class="col text-end fs-5 offset-md-3 justify-content-end">
                            <p style="margin:1px;">
                                <#assign strengthLevelNum = item.strengthLevel?number>
                                <#list 1..strengthLevelNum as num>
                                    <img src="http://127.0.0.1:2333/resources/img/star.png" width="25" height="25" alt=""/>
                                </#list>
                            </p>
                            <#list item.permanentEnchant! as permanentEnchant>
                                <p style="margin:1px;color:cornflowerblue;">${permanentEnchant.name}</p>
                            </#list>
                            <p style="margin:1px;">
                                <#list item.fiveStone! as icon>
                                    <img src="${icon.icon}" width="30" height="30">
                                </#list>
                            </p>
                        </div>
                    </div>
                    <hr>
                </#list>

            </div>
            <!--角色奇穴-->
            <div style="margin-top:10px;"></div>
            <div class="row">
                <#list qixueList! as item>
                    <div class="col-1 text-center fs-5">
                        <img src="${item.icon}" width="50" height="50" style="margin-top:20px;">
                        <p>${item.name}</p>
                    </div>
                </#list>
            </div>
        </div>
        <div class="card-footer">
            <h6 class="text-center fs-3">数据来源：www.jx3api.com</h6>
        </div>
    </div>
</div>
</body>

</html>
