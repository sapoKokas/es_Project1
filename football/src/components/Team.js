import React,{ Component } from "react";
import Table from "reactstrap/lib/Table";

class Team extends Component{
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
        //console.log(param.get('id'))
        fetch('http://localhost:8080/team/?team_key=' + param.get('id'))
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
                                <th>player_name</th>
                                <th>player_number</th>
                                <th>player_country</th>
                                <th>player_type</th>
                                <th>player_age</th>
                                <th>player_match_played</th>
                                <th>player_goals</th>
                            </tr>
                            {this.state.data.map(c=>
                                <tr>
                                    <th>{c.player_name}</th>
                                    <th>{c.player_number}</th>
                                    <th>{c.player_country}</th>
                                    <th>{c.player_type}</th>
                                    <th>{c.player_age}</th>
                                    <th>{c.player_match_played}</th>
                                    <th>{c.player_goals}</th>
                                </tr>
                            )}
                        </Table>
                    </div>
            </div>
        );
    }
}

export default Team;