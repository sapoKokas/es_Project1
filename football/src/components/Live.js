import React,{ Component } from "react";
import Table from "reactstrap/lib/Table";

class Live extends Component{
    constructor(){
        super();
        this.state={
            loading:true,
            data : []
        };
    }

    componentDidMount(){
        fetch('http://localhost:8080/live/')
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
                                        <li class="nav-item active"><a class="nav-link" href="/">Live</a></li>
                                        <li class="nav-item"><a class="nav-link" href="/countries">Countries</a></li>
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
                    <h1 style={{display: 'flex',  justifyContent:'center', alignItems:'center'}}>Team Info</h1>

                    <div style={{display: 'flex',  justifyContent:'center', alignItems:'center'}}>
                        <Table style={{marginTop:'-15px',width:"80%", alignSelf:"center"}}>
                            <tr>
                                <th>match_hometeam_name</th>
                                <th>match_awayteam_name</th>
                                <th>match_hometeam_score</th>
                                <th>match_awayteam_score</th>
                                <th>match_status</th>
                                <th>league_name</th>
                                <th>country_name</th>
                                <th>match_time</th>
                                <th>match_date</th>
                            </tr>
                            {this.state.data.map(c=>
                                <tr>
                                    <th>{c.match_hometeam_name}</th>
                                    <th>{c.match_awayteam_name}</th>
                                    <th>{c.match_hometeam_score}</th>
                                    <th>{c.match_awayteam_score}</th>
                                    <th>{c.match_status}</th>
                                    <th>{c.league_name}</th>
                                    <th>{c.country_name}</th>
                                    <th>{c.match_time}</th>
                                    <th>{c.match_date}</th>
                                </tr>
                            )}
                        </Table>
                    </div>
            </div>
        );
    }
}

export default Live;