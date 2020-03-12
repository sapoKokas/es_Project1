import React,{ Component } from "react";


class Country extends Component{
    constructor(){
        super();
        this.state={
            loading: true,
            data : []
        };
    }
    componentDidMount(){
        const query = window.location.search;
        const param = new URLSearchParams(query);
        fetch('http://localhost:8080/country/leagues/?country_id=' + param.get('id'))
        .then(response => response.json())
        .then(data => this.setState({ data : data, loading:false}));
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
                                        <li class="nav-item active"><a class="nav-link" href="/countries">Countries</a></li>
                                        <li class="nav-item"><a class="nav-link" href="/leagues">Leagues</a></li>
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

                    <section class="blog-post-area section-margin">
                        <div class="container">
                            <div class="col-lg-12">
                                <div class="row">

                                    {this.state.data.map(c=>
                                        <div class="col-md-3">
                                        <div class="single-recent-blog-post card-view">
            
                                        <div class="thumb">
                                                    
                                                    <img class="card-img rounded-0" src="img/qlqmerda.png" alt="" style={{ backgroundColor: "rgb("+Math.random()*255+","+Math.random()*255+","+Math.random()*255+")"}}/>
                                            
                                            <ul class="thumb-info">
                                                <li><a href={"/league?id="+ c.league_id}>{c.league_name}</a></li>
                                            </ul>
                                            </div>
                                       
                                    </div>
                                    </div>
                                    )}
                            </div>
                            </div>
                        </div>
                    </section>
            </div>        
        );
     }
}

export default Country;