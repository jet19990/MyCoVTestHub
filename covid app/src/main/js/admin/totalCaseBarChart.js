import {useEffect, useState} from "react";
import {Chart} from "react-google-charts";

const React = require('react');

export function TotalCasesBarChart(props) {

    const [ageGroupPostCodeData, setAgeGroupPostCodeData] = useState([]);

    useEffect(() => {
        const data = [
            ['Case Type', 'Amount'],
            ['Positive', props.totalPositive],
            ['Negative', props.totalNegative],
            ['Inconclusive', props.totalInconclusive],
        ];
        setAgeGroupPostCodeData(data);


    }, [])


    return (<Chart
        width={'700px'}
        height={'500px'}
        chartType="BarChart"
        loader={<div>Loading Chart</div>}
        data={ageGroupPostCodeData}
        options={{
            title: 'Total cases by case type',
            chartArea: {width: '50%'},
            isStacked: true,
            hAxis: {
                title: 'Number of Cases',
                minValue: 0,
            },
            vAxis: {
                title: 'Case Type',
            },
        }}
        // For tests
        rootProps={{'data-testid': '3'}}
    />)
}