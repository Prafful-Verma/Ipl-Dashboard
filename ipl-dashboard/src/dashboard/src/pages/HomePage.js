import {React, useEffect, useState} from 'react';

import {TeamTile} from '../components/TeamTile';

import './HomePage.scss';

export const HomePage = () => {

    const [teams, setTeams] = useState([]);

    useEffect(

                () => {

                    const getTeams = async () => {

                        const response = await fetch(`http://localhost:8080/team/allTeams`);
                        const data = await response.json();
                        setTeams(data);
                    };

                    getTeams();

                }, []
            );


  return (
    <div className="HomePage">

        <div className="homepage-header">
            <h1 className="app-name"> IPL Dashboard Application</h1>
        </div>

        <div className="team-grid">
            {teams.map(team => <TeamTile teamName={team.teamName} />)}
        </div>

    </div>
  );
}