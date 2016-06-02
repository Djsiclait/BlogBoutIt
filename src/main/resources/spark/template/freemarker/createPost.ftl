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
            <div id="login">
                <fieldset>
                    <legend>Login to Start to Blog</legend>
                    <br>
                    <form class="col s12">
                        <div class="row">
                            <div class="input-field col s12">
                                <input id="title" type="text" class="validate">
                                <label for="title">Title</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="input-field col s12">
                                <textarea id="textarea1" class="materialize-textarea"></textarea>
                                <label for="textarea1">Textarea</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="input-field col s6">
                                <input placeholder="Tags" id="first_name" type="text" class="validate">
                                <label for="first_name">Tags</label>
                            </div>
                        </div>
                        <div class="divider"></div>
                        <div class="row">
                            <div class="col m12">
                                <p class="right-align">
                                    <button class="btn btn-large waves-effect waves-light" type="button" name="action">
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
</div>





<!-- Footer -->
<#include "/footer.ftl">


</body>
</html>