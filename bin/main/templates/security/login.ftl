<#import "/spring.ftl" as spring/>
<#include "../general/header.ftl">

<div class="conteneur">
    <div class="center-contenu">
    <h1>Se connecter</h1>
    <br>
    <form action="/login" method="POST">
        <label>Identifiant :</label>
        <input class="form-control" type='text' name='username'/>
        <label>Mot de passe :</label>
        <input class="form-control" type='password' name='password'/>
        <input type="hidden"
               name="${_csrf.parameterName}"
               value="${_csrf.token}"/>
            <br>
            <input class="btn btn-success" name="submit" type="submit" value="Connexion">
        </form>

    <#if Session.SPRING_SECURITY_LAST_EXCEPTION?? && Session.SPRING_SECURITY_LAST_EXCEPTION.message?has_content>
    <h1>Bad credential</h1>
    </#if>
    </div>
</div>
