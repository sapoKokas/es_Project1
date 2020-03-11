import React,{ Component } from "react";


class Leagues extends Component{
    constructor(){
        super();
        this.state={

        };
    }

    render(){
        return (
            <div>
                <header class="header_area">
                <div class="main_menu">
                    <nav class="navbar navbar-expand-lg navbar-light">
                        <div class="container box_1620">
                            <a class="navbar-brand logo_h" href="index.html"><img class="logo" src="img/logo.png" alt="" /></a>
                            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                                <span class="icon-bar"></span>
                                <span class="icon-bar"></span>
                                <span class="icon-bar"></span>
                            </button>
                            <div class="collapse navbar-collapse offset" id="navbarSupportedContent">
                                    <ul class="nav  menu_nav ">
                                        <li class="nav-item "><a class="nav-link" href="/">Live</a></li>
                                        <li class="nav-item"><a class="nav-link" href="/countries">Countries</a></li>
                                        <li class="nav-item active"><a class="nav-link" href="/leagues">Leagues</a></li>
                                    </ul>
                            </div>
                        </div>
                     </nav>
                    </div>
                 </header>

                <main class="site-main" />
                    <section class="mb-30px">
                        <div class="container">
                            <div class="hero-banner">
                            </div>
                        </div>
                    </section>
            </div>
        );
    }
}

export default Leagues;