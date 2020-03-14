import React,{ Component } from "react";
import Table from "reactstrap/lib/Table";

async function fetchURLs() {
    const query = window.location.search;
    const param = new URLSearchParams(query);
    var data2 = [];
    try {
        // Promise.all() lets us coalesce multiple promises into a single super-promise

        var data = await Promise.all([
            fetch('http://localhost:8080/league/?league_id=' + param.get('id')).then((response) => response.json()),// parse each response as json
            fetch('http://localhost:8080/league/standings/?league_id=' + param.get('id')).then((response) => response.json())
        ]);
        for (var i of data) {
            data2.push(i);
          }
        return data2;
       
      } catch (error) {
        console.log(error);
      }
      return null;
    }

class League extends Component{
    constructor(){
        super();
        this.state={
            loading: true,
            data : [],
            data2 : []
        };
    }

    
    componentDidMount(){
        fetchURLs().then( (c) =>
            this.setState({data : c[0], data2 : c[1], loading:false})
        );
       
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
            
                                        { (c.team_badge===""?
                                            (
                                            <div class="thumb">
                                                        <img class="card-img rounded-0" src="img/default-country.jpg" alt=""/>
                                                
                                                <ul class="thumb-info">
                                                    <li><a href={"/team?id=" + c.team_key}>{c.team_name}</a></li>
                                                </ul>
                                                </div>
                                            ):( 
                                                <div class="thumb">
                                                <img class="card-img rounded-0" src={c.team_badge} alt=""/>      
                                                    <ul class="thumb-info">
                                                        <li><a href={"/team?id=" + c.team_key}>{c.team_name}</a></li>
                                                    </ul>
                                                </div>
                                                
                                                )
                                            )}    
                                    </div>
                                    </div>
                                    )}
                            </div>
                            </div>
                        </div>
                        <h1 style={{display: 'flex',  justifyContent:'center', alignItems:'center'}}>League Table</h1>
                    </section>
                   
                    <div style={{display: 'flex',  justifyContent:'center', alignItems:'center'}}>
                        <Table style={{marginTop:'-15px',width:"80%", alignSelf:"center"}}>
                            <tr>
                                <th>Pos</th>
                                <th>Name</th>
                                <th>MP</th>
                                <th>W</th>
                                <th>D</th>
                                <th>L</th>
                                <th>Pts</th>
                            </tr>
                            {this.state.data2.map(c=>
                                <tr>
                                    <th>{c.overall_league_position}</th>
                                    <th>{c.team_name}</th>
                                    <th>{c.overall_league_payed}</th>
                                    <th>{c.overall_league_W}</th>
                                    <th>{c.overall_league_D}</th>
                                    <th>{c.overall_league_L}</th>
                                    <th>{c.overall_league_PTS}</th>
                                </tr>
                            )}
                        </Table>
                        </div>
            </div>        
        );
     }
}

export default League;