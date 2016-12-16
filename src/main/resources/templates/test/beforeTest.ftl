<#include "/part/header.ftl">
<form action="/" method="get">
    <input type="submit" value="На главную">
</form>
<h1>Тест</h1>
<div class="row">
    <div class="col-md-6 col-md-offset-3">
        <form name="choose" role="form" action="/pair/choose" method="post">
            <div class="form-group">
                <label>Укажите количество слов</label>
                <input min="1" required   id = "count"   type="number" class="form-control"    name="count"   value="10">
            </div>
            <div class="form-group">
                <label>Тренировать добавленные Вами слова или из словаря?</label>
                <br>
                <input checked id="fromClient" type="radio" name="from" value="fromClient"> <label title="Если вы указали больше слов, чем добавили, список будет дополнен словами из словаря" for="fromClient">Мои</label>
                <br>
                <input id="fromDictionary" type="radio" name="from" value="fromDictionary"> <label for="fromDictionary" >Ваши</label>
            </div>
            <input type="submit" class="btn btn-primary btn-lg" id="submit" value="Запустить тест">
        </form>
    </div>
</div>
<#include "/part/footer.ftl">