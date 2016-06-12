<#include "/siteHeader.ftl">
<body>
<!-- Header -->
<#--
<div id="index-banner" class="parallax-container">
    <div class="the-index-header"><#include "/navbar.ftl"></div>
    <div class="section no-pad-bot">
        <div class="container ed-container">


            <br><br>
            <h1 class="header center orange-text accent-2 text-lighten-2">${message} ${user.getName()}.</h1>
            <div class="row center">
                <h5 class="header col s12 light">The blog about some of the coolest stuff</h5>
            </div>
            <div class="row center">
                <a href="/register" id="download-button" class="btn-large waves-effect waves-light orange accent-2">Register</a>
            </div>
            <div class="row center">
                <h7 class="header col s12 light grey-text">Scroll Down to view content</h7>
            </div>
            <br><br>


        </div>
    </div>
    <div class="parallax"><img src="http://designninjaz.com/wp-content/uploads/2015/01/Slider-BlackDeskScene.jpg"
                               alt="Unsplashed background img 1"></div>
</div>
-->
<div id="index-banner" class="parallax-container">
    <div class="the-index-header"><#include "/navbar.ftl"></div>
    <div class="section no-pad-bot">
        <div class="container ed-container">


            <br><br>
            <h1 class="header center orange-text accent-2 text-lighten-2">${message} ${user.getName()}.</h1>
            <div class="row center">
                <h5 class="header col s12 light">The blog about some of the coolest stuff</h5>
            </div>
            <div class="row center">
                <a href="/register" id="download-button" class="btn-large waves-effect waves-light orange accent-2">Register</a>
            </div>
            <div class="row center">
                <h7 class="header col s12 light grey-text">Scroll Down to view content</h7>
            </div>
            <br><br>


        </div>
    </div>
    <div class="parallax">
        <div class="homepage-hero-module">
            <div class="video-container">
                <div class="filter"></div>
                <video autoplay loop class="fillWidth">
                    <source src="/media/Mp4/Going-Places.mp4" type="video/mp4"/>
                    Your browser does not support the video tag. I suggest you upgrade your browser.
                    <source src="/media/Webm/Going-Places.webm" type="video/webm"/>
                    Your browser does not support the video tag. I suggest you upgrade your browser.
                </video>
                <div class="poster hidden">
                    <img src="/media/Snapshots/Going-Places.jpg" alt="">
                </div>
            </div>
        </div>
    </div>
</div>


<!-- End Header -->


<div class="row" id="mainContent">
<#list listaArticulos?reverse as articulo>

    <div class="col s12 m6" id="blog-post">

        <div class="card hoverable">
            <div class="card-image waves-effect waves-block waves-light">
                <a class="modal-trigger" href="#modal${articulo.getId()}">
                    <img class="" src="http://loremflickr.com/800/400">
                </a>
            </div>
            <div class="card-content">
            <span class="activator card-title grey-text text-darken-4">
                <a class="modal-trigger" href="#modal${articulo.getId()}">${articulo.getTitle()}</a>
                <i class="material-icons right">comment</i></span>

                <#assign mini=articulo.getBody()>
                <#if mini?length &lt; 350>
                    <p>${mini}</p>
                <#else>
                    <p>${mini?substring(0,349)} ... </p>
                </#if>

            </div>
            <div class="card-reveal">
            <span class="card-title grey-text text-darken-4">Comments<i
                    class="material-icons right">close</i></span>


                <div class="row" id="comment-container">
                    <#list articulo.getComments()?reverse as comment>
                        <div class="col s12 m6">
                            <div class="card-panel teal hoverable">
                      <span class="white-text ">

                          <h5>
                          <div class="row">
                          ${comment.getAuthor().getUsername()}:
                              <#if user.isAdmin()>
                                  <form class="spacer" action="" method="post">
                                          <input type="hidden" id="commentID" name="commentID"
                                                 value="${comment.getId()}">
                                          <input type="hidden" id="kind" name="kind" value="delete">
                                          <input class="waves-effect waves-teal btn-flat" type="submit" value="X">
                                      </form>
                              </#if>
                              </div>
                          </h5>

                          <div class="divider"></div>
                          <br>
                      ${comment.getComment()}
                      </span>
                            </div>
                        </div>
                    </#list>
                </div>


                <div class="row">
                    <#if user.getName() == "guest">
                        <h5>Login/register to start commenting</h5>
                    <#else>
                        <form class="col s12" action="" method="POST" id="commentForm">
                            <div class="row">
                                <div class="input-field col s12">
                                    <input type="hidden" id="kind" name="kind" value="create">
                                    <input type="hidden" name="postID" value="${articulo.getId()}">
                                <textarea type="input" id="textarea1" value="Sample comment" name="thebodyx"
                                          class="materialize-textarea"></textarea>
                                    <label for="textarea1">Textarea</label>
                                </div>
                            </div>
                            <input type="hidden" name="user" id="user" value="${user.getUsername()}">
                            <button class="btn waves-effect waves-light" type="submit" name="action">Submit
                                <i class="material-icons right">send</i>
                            </button>
                        </form>
                    </#if>
                </div>


            </div>

            <#list articulo.getTags() as tag>

                <div class="chip">
                ${tag.getTag()}
                </div>

            </#list>


            <div class="card-action">
                <#if user.isAdmin()>
                    <a class="red-text" href="#">Delete Post</a>
                    <a href="#">Edit Post</a>
                </#if>
                <form class="left" action="" method="post">
                    <input type="hidden" id="kind" name="kind" value="like">
                    <input class="btn-flat" type="submit" value="Like">
                </form>
                <form class="right" action="" method="post">
                    <input type="hidden" id="kind" name="kind" value="dislike">
                    <input class="btn-flat" type="submit" value="Dislike">
                </form>

            </div>


        </div>

    </div>


</#list>
</div>

<!----------modals-------------->

<#list listaArticulos?reverse as articulo>
<div id="modal${articulo.getId()}" class="modal">
    <div class="modal-content">
        <h4>${articulo.getTitle()}</h4>
        <p>${articulo.getBody()}</p>
    </div>
    <div class="modal-footer">
        <a href="#!" class=" modal-action modal-close waves-effect waves-green btn-flat">Close</a>
    </div>
</div>

</#list>


<br><br>

<ul class="pagination center-align">
    <li class="disabled"><a href="#!"><i class="material-icons">chevron_left</i></a></li>
    <li class="active"><a href="#!">1</a></li>
    <li class="waves-effect"><a href="#!">2</a></li>
    <li class="waves-effect"><a href="#!">3</a></li>
    <li class="waves-effect"><a href="#!">4</a></li>
    <li class="waves-effect"><a href="#!">5</a></li>
    <li class="waves-effect"><a href="#!"><i class="material-icons">chevron_right</i></a></li>
</ul>
<!-- Footer -->
<#include "/footer.ftl">


</body>
</html>