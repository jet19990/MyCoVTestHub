import {useEffect, useState} from "react";

const React = require('react');
import { Chart } from "react-google-charts";

export function AgeGroupPostCodeDistribution(props) {

    const [ageGroupPostCodeData, setAgeGroupPostCodeData] = useState([]);

    useEffect(() => {

        const datalist = props.casesByAgeGroupPerPostCode;
        datalist.unshift(['Post Code', '0 - 10 Age Group', '10 - 20 Age Group', '20 - 40 Age Group', '40 - 60 Age Group', '60 < Age Group']);
        setAgeGroupPostCodeData(datalist);
    },[])


    return(<Chart
        width={'700px'}
        height={'500px'}
        chartType="BarChart"
        loader={<div>Loading Chart</div>}
        data={ageGroupPostCodeData}
        options={{
            title: 'Positive cases distribution by postcode/age group',
            chartArea: { width: '50%' },
            isStacked: true,
            hAxis: {
                title: 'Positive Cases',
                minValue: 0,
            },
            vAxis: {
                title: 'Post Code',
            },
        }}
        // For tests
        rootProps={{ 'data-testid': '3' }}
    />)
}