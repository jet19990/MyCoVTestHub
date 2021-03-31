import {useEffect, useState} from "react";
import {Chart} from "react-google-charts";

const React = require('react');

export function AgeGroupDistribution(props) {

    const [ageGroupData, setAgeGroupData] = useState([]);

    useEffect(() => {

        const datalist = props.casesByAgeGroup.map(i => [i.ageGroup,i.cases])
        datalist.unshift(["Age Group", "Count"]);
        setAgeGroupData(datalist);
    },[])

    return (<div style={{display: 'flex', maxWidth: 900}}>
            <Chart
                width={'700px'}
                height={'700px'}
                chartType="PieChart"
                loader={<div>Loading Chart</div>}
                data={ageGroupData}
                options={{
                    title: 'Positive Cases By Age Group',
                }}
                rootProps={{'data-testid': '2'}}
            />
        </div>
    )

}