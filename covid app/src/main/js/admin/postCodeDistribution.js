import {useEffect, useState} from "react";
import {Chart} from "react-google-charts";

const React = require('react');

export function PostCodeDistribution(props) {

    const [charData, setCharData] = useState([]);

    useEffect(() => {

        const datalist = props.casesByPostCodeList.map(i => [i.postCode,i.count])
        datalist.unshift(["Post Code", "Count"]);
        setCharData(datalist);
    },[])

    return (<div style={{display: 'flex', maxWidth: 900}}>
            <Chart
                width={'700px'}
                height={'700px'}
                chartType="PieChart"
                loader={<div>Loading Chart</div>}
                data={charData}
                options={{
                    title: 'Positive Cases By Post Code',
                }}
                rootProps={{'data-testid': '1'}}
            />
        </div>
    )
}