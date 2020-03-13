import React from 'react';
import logo from './logo.svg';
import './App.css';
import Live from './components/Live';
import Countries from './components/Countries';
import Leagues from './components/Leagues';
import Country from './components/Country';
import League from './components/League';
import Standings from './components/Standings';
import {
    BrowserRouter as Router,
    Switch,
    Route,
    Link
} from "react-router-dom";

function App() {
    return ( <Router >
                <Switch >
                <Route path = "/leagues" >
                    <Leagues />
                </Route>
                <Route path = "/countries" >
                    <Countries />
                </Route> 
                <Route path = "/country" >
                    <Country />
                </Route>
                <Route path = "/league" >
                    <League />
                </Route>
                <Route path = "/league/standings" >
                    <Standings/>
                </Route>
                <Route path = "/" >
                    <Live />
                </Route> 
                </Switch> 
         </Router>
    );
}

export default App;