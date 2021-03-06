<#include "/siteHeader.ftl">
<body>
<!-- Header -->

<#include "/navbar.ftl">
<div id="header">
    <h1>${message}</h1>
</div>

<div id="container">
    <br>

    <!-- Login Form -->
<#if user.getName() == "guest">

    <div class="row someform">
        <div class="col s12">
            <div id="login">
                <fieldset>
                    <legend>Login to Start to Blog</legend>
                    <br>
                    <form class="col s12" method="POST" action="">
                        <div class="row">
                            <div class="input-field col s12">
                                <input id="username" name="username" type="text" class="validate" required/>
                                <label for="username">User Name</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="input-field col s12">
                                <input id="password" name="password" type="password" class="validate" required/>
                                <label for="password">Password</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col s12">
                                <p>
                                    <input type="checkbox" name="checkbox" id="remember">
                                    <label for="remember">Remember me</label>
                                </p>
                            </div>
                        </div>
                        <div class="divider"></div>
                        <div class="row">
                            <div class="col m12">
                                <p class="right-align">
                                    <button class="btn btn-large waves-effect waves-light" type="submit" name="action">
                                        Login
                                    </button>
                                </p>
                            </div>
                        </div>
                    </form>
                </fieldset>
            </div>
        </div>
    </div>
<#else>
    <h1>YOU ARE ALREADY LOGGED IN        <a href="/logout">LOG OUT</a></h1>
</#if>
</div>

<br>

<div>
    <!-- <img src="img/write.jpg" alt="Image Not Found" /> -->
</div>


<!-- Footer -->
<#include "/footer.ftl">

</body>
</html>