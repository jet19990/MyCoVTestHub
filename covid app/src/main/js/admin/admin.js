import {PositiveNegativeCases} from "./positiveNegativeCases";
import {Col, Container, Row} from "react-bootstrap";
import {useEffect, useState} from "react";
import {PostCodeDistribution} from "./postCodeDistribution";
import {AgeGroupDistribution} from "./ageGroupDistrubution";
import {AgeGroupPostCodeDistribution} from "./ageGroupPostCodeDistribution";
import '../mainStyle.css';
import {TotalCasesBarChart} from "./totalCaseBarChart";

const axios = require('axios');
const React = require('react');

export function Admin(props) {

    const [totalNegative, setTotalNegative] = useState(0);
    const [totalPositive, setTotalPositive] = useState(0);
    const [totalInconclusive, setTotalInconclusive] = useState(0);
    const [casesByPostCodeList, setCasesByPostCodeList] = useState([]);
    const [casesByAgeGroupList, setCasesByAgeGroupList] = useState([]);
    const [casesByAgeGroupPerPostCode, setCasesByAgeGroupPerPostCode] = useState([]);

    useEffect(() => {
        axios.get('/admin/summary')
            .then(function (response) {
                if (typeof response.data != 'string') {
                    setTotalPositive(response.data.totalPositiveCases);
                    setTotalNegative(response.data.totalNegativeCases);
                    setTotalInconclusive(response.data.totalInconclusiveCases);
                    setCasesByPostCodeList(response.data.casesByPostCodeList);
                    setCasesByAgeGroupList(response.data.casesByAgeGroupsList);
                    setCasesByAgeGroupPerPostCode(response.data.casesByAgeGroupPerPostCode);
                } else {
                    props.logout();
                }
            })
            .catch(function (error) {
                console.log(error);
                props.logout();
            })
            .then(function () {
            });

    }, []);

    return (
        <div className="main-container">
            {casesByPostCodeList &&
            <Container fluid className="white-bg round-edge">
                <Row className="add-space-top">
                    <Col/>
                    <Col xs={11}>
                        <PositiveNegativeCases totalPositive={totalPositive} totalNegative={totalNegative}
                                               totalInconclusive={totalInconclusive}/>
                    </Col>
                    <Col/>
                </Row>
                <Row className="justify-content-md-center">
                    {casesByAgeGroupPerPostCode.length > 0 &&
                    <Col md="auto">
                        <TotalCasesBarChart totalPositive={totalPositive} totalNegative={totalNegative}
                                            totalInconclusive={totalInconclusive}/>
                    </Col>}
                    {casesByAgeGroupPerPostCode.length > 0 &&
                    <Col md="auto">
                        <AgeGroupPostCodeDistribution casesByAgeGroupPerPostCode={casesByAgeGroupPerPostCode}/>
                    </Col>
                    }
                    <Col/>
                </Row>
                <Row className="justify-content-md-center">
                    {casesByPostCodeList.length > 0 &&
                    <Col md="auto">
                        <PostCodeDistribution casesByPostCodeList={casesByPostCodeList}/>
                    </Col>
                    }
                    {casesByAgeGroupList.length > 0 &&
                    <Col md="auto">
                        <AgeGroupDistribution casesByAgeGroup={casesByAgeGroupList}/>
                    </Col>
                    }
                </Row>
            </Container>}
        </div>
    );
}