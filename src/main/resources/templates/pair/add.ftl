<#include "/part/header.ftl">
<form action="/" method="get">
    <input type="submit" value="На главную">
</form>
<h1>Вы можете ввести пару слов(рус/англ)</h1>
<div class="row">
    <div class="col-md-6 col-md-offset-3">
        <form name="pair" role="form" action="/pair/add" method="post">
            <div class="form-group">
                <label>Русский</label>
                <input id="ruInput"  type="text"     class="form-control"    name="ruWord"   value="">
            </div>
            <div class="form-group">
                <label>English</label>
                <input  id = "enInput"   type="text" class="form-control"    name="enWord"   value="">
            </div>
            <input type="submit" class="btn btn-primary btn-lg" id="submit" value="Добавить" style="visibility: hidden">
        </form>
        <button id="translate">Перевести</button>
    </div>
</div>
<form action="/pair/test" method="get">
    <input type="submit" value="Запустить тест!">
</form>
<#include "/pair/addText.ftl">
<#include "/pair/addFile.ftl">
<script>

    document.getElementById("ruInput").onblur=function () {
        if ((this.value.length > 0) && (document.getElementById("enInput").value.length == 0)) {
            document.getElementById("enInput").disabled = true;
        }
        else{
            document.getElementById("enInput").disabled = false;
        }
    }
    document.getElementById("enInput").onblur=function () {
        if ((this.value.length > 0) && (document.getElementById("ruInput").value.length == 0)) {
            document.getElementById("ruInput").disabled = true;
        }
        else{
            document.getElementById("ruInput").disabled = false;
        }
    }
    document.getElementById("translate").onclick=function () {
        document.getElementById("ruInput").disabled = false;
        document.getElementById("enInput").disabled = false;
        document.getElementById("submit").style.visibility = "visible";
        document.getElementById("translate").disabled = true;
        var xmlHttp = new XMLHttpRequest();

        if (document.getElementById("enInput").value.length > 0){
            xmlHttp.open( "GET", "/translate/"+document.getElementById("enInput").value + "/en-ru", false ); // false for synchronous request
            xmlHttp.send( null );
            document.getElementById("ruInput").value = xmlHttp.responseText;
        }
        else {
            xmlHttp.open( "GET", "/translate/"+document.getElementById("ruInput").value + "/ru-en", false ); // false for synchronous request
            xmlHttp.send( null );
            document.getElementById("enInput").value = xmlHttp.responseText;
        }
    }
</script>
<#include "/part/footer.ftl">