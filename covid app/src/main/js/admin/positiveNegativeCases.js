import {Badge, Col, Container, Row} from "react-bootstrap";

const React = require('react');

export function PositiveNegativeCases(props) {
    return (
        <Container fluid>
            <Row className="justify-content-md-center">
                <Col md="auto">
                    <h3>Positive Cases <Badge variant="danger">{props.totalPositive}</Badge></h3>
                </Col>
                <Col md="auto">
                    <h3>Negative Cases <Badge variant="primary">{props.totalNegative}</Badge></h3>
                </Col>
                <Col md="auto">
                    <h3>Inconclusive Cases <Badge variant="primary">{props.totalInconclusive}</Badge></h3>
                </Col>
            </Row>
        </Container>

    );
}