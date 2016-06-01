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
    <div class="row">
    <div class="col s16 m4">
    <div id="login">

        <fieldset>
            <legend>Login to Start to Blog</legend>
            <br>
            <form class="col s12">
                <div class="row">
                    <div class="input-field col s12">
                        <input id="email" type="email" class="validate">
                        <label for="email">Email</label>
                    </div>
                </div>
                <div class="row">
                    <div class="input-field col s12">
                        <input id="password" type="password" class="validate">
                        <label for="password">Password</label>
                    </div>
                </div>
                <div class="row">
                    <div class="col s12">
                        <p>
                            <input type="checkbox" id="remember">
                            <label for="remember">Remember me</label>
                        </p>
                    </div>
                </div>
                <div class="divider"></div>
                <div class="row">
                    <div class="col m12">
                        <p class="right-align">
                            <button class="btn btn-large waves-effect waves-light" type="button" name="action">Login</button>
                        </p>
                    </div>
                </div>
            </form>
        </fieldset>
    </div>
        </div>
    </div>
    </div>

    <br>

    <div>
        <!-- <img src="img/write.jpg" alt="Image Not Found" /> -->
    </div>


<!-- Footer -->
<#include "/footer.ftl">

</body>
</html>