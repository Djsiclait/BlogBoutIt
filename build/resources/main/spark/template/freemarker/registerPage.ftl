<#include "/siteHeader.ftl">
<body>

<#include "/navbar.ftl">
<!-- Header -->
<div id="header">
    <h1>${message}</h1>
</div>



<div id="container">
    <br>

    <!-- Login Form -->
    <div class="row someform">
        <div class="col s12">


                <fieldset id="register-form">
                    <legend><h5>Register to start posting!</h5></legend>
                    <br>
                    <form method="POST" action="" class="col s12">
                        <div class="row">
                            <div class="input-field col s12">
                                <input id="username" name="username" type="text" class="validate">
                                <label for="username">User Name</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="input-field col s12">
                                <input id="name" name="name" type="text" class="validate">
                                <label for="name">Name</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="input-field col s12">
                                <input id="password" name="password" type="password" class="validate">
                                <label for="password">Password</label>
                            </div>
                        </div>
                        <div class="divider"></div>
                        <p>
                            <input type="checkbox" id="admin" name="admin"/>
                            <label for="admin">Admin</label>
                        </p>
                        <p>
                        <input type="checkbox" id="author" name="author"/>
                        <label for="author">Author</label>
                    </p>
                        <div class="divider"></div>
                        <div class="row">
                            <div class="col m12">
                                <p class="right-align">
                                    <button class="btn btn-large waves-effect waves-effect" type="submit"   name="action">Register <i class="material-icons right">send</i></button>
                                </p>
                            </div>
                        </div>
                    </form>
                </fieldset>
            </div>
    </div>
    <br><br>
</div>


<!-- Footer -->
<#include "/footer.ftl">

</body>
</html>