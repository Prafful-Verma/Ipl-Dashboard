import {React} from 'react';
import { Link} from 'react-router-dom';

import './YearSelectorCard.scss';

export const YearSelectorCard = ({teamName}) => {

    var years = [];

    const START_YEAR = 2008;
    const END_YEAR = 2020;

    for(var i=START_YEAR;i<=END_YEAR;i++) {
        years.push(i);
    }

    return (

        <ol className = "YearSelectorCard">
            {years.map( year => (
                <li key={year}>
                    <Link  to={`/team/${teamName}/match/${year}`}>{year}</Link>
                </li>
            ))}
        </ol>
    );

}