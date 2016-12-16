<#include "/part/header.ftl">

<div class="row">
    <div class="col-xs-4 col-xs-offset-4">
        <div class="alert alert-danger" role="alert">
        <strong>INTERNAL ERROR</strong>
        <p>${error!}</p>
        </div>
    </div>
    <form action="/" method="get">
        <input type="submit" value="На главную">
    </form>
</div>

<#include "/part/footer.ftl">