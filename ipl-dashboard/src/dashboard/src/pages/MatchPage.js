import {React, useEffect, useState} from 'react';
import {useParams, Link} from 'react-router-dom';
import {MatchDetailCard} from '../components/MatchDetailCard';
import {YearSelectorCard} from '../components/YearSelectorCard';

import './MatchPage.scss';

export const MatchPage = () => {

    const [matches, setMatches] = useState([]);

    const {teamName, year} = useParams();

    useEffect(

            () => {

                const getMatches = async () => {

                    const response = await fetch(`http://localhost:8080/team/${teamName}/matches?year=${year}`);
                    const data = await response.json();
                    setMatches(data);
                };

                getMatches();

            }, [teamName, year]
        );

  return (
    <div className="MatchPage">

    <div className="year-selector-card">
        <h3> Select Year </h3>
        <YearSelectorCard teamName={teamName}/>
    </div>

    <div>
      <h1 className="page-heading">{teamName} matches in {year}</h1>

      <h1 className="home-page-link">
            <Link to={`/`}>Home</Link>
      </h1>

      {
        matches.map(match => <MatchDetailCard teamName={teamName} match={match}/>)
      }
     </div>
    </div>
  );
}