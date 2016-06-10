<div class="loader">
    <div class="preloader-wrapper active">
        <div class="spinner-layer spinner-red-only">
            <div class="circle-clipper left">
                <div class="circle"></div>
            </div>
            <div class="gap-patch">
                <div class="circle"></div>
            </div>
            <div class="circle-clipper right">
                <div class="circle"></div>
            </div>
        </div>
    </div>
</div>
<div class="navbar-fixed fixed">
    <nav class="grey lighten-3">
        <div class="nav-wrapper">
            <a href="#" data-activates="mobile-demo" class="button-collapse"><i class="material-icons">menu</i></a>
            <a id="navbar-logo" href="/" class="brand-logo orange-text accent-2"> BlogBoutIt</a>
            <ul class="right hide-on-med-and-down">
                <li><a class="orange-text darken-4" href="/">Home</a></li>
                <#if user.getName() == "Guest">
                    <li><a  class="orange-text darken-4" href="/register">Register</a></li>
                    <li><a  class="orange-text darken-4" href="/login">Login</a></li>
                <#else>
                    <#if user.isAdmin()||user.isAuthor()>
                    <li><a  class="orange-text darken-4" href="/create">Create Post</a></li>
                    </#if>
                    <li><a  class="orange-text darken-4" href="/logout">Logout</a></li>
                </#if>
            </ul>
            <ul class="side-nav" id="mobile-demo">
                <li><a class="orange-text darken-4" href="/">Home</a></li>
            <#if user.getName() == "Guest">
                <li><a  class="orange-text darken-4" href="/register">Register</a></li>
                <li><a  class="orange-text darken-4" href="/login">Login</a></li>
            <#else>
                <#if user.isAdmin()||user.isAuthor()>
                    <li><a  class="orange-text darken-4" href="/create">Create Post</a></li>
                </#if>
                <li><a  class="orange-text darken-4" href="/logout">Logout</a></li>
            </#if>
            </ul>
        </div>
    </nav>
</div>