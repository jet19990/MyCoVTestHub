import {useState} from "react";
import {Button, Col, Container, Form, Row, Modal} from 'react-bootstrap';
import '../mainStyle.css';

const React = require('react');
const axios = require('axios');

export function SubmitTestResults(props) {

    const [email, setEmail] = useState("");
    const [fullName, setFullName] = useState("");
    const [age, setAge] = useState("");
    const [address, setAddress] = useState("");
    const [postCode, setPostCode] = useState("");
    const [ttn, setTtn] = useState("");
    const [gender, setGender] = useState("Male");
    const [result, setResult] = useState("Positive");
    const [showError, setShowError] = useState(false);
    const [errorMsg, setErrorMsg] = useState("");
    const [showSuccess, setShowSuccess] = useState(false);

    const onCreate = (testResult) => {
        axios.post('/test-results', testResult)
            .then(function (response) {
                setShowSuccess(true);
                setFullName('');
                setEmail('');
                setAge('');
                setGender("Male");
                setAddress('');
                setPostCode('');
                setTtn('');
                setResult("Positive")
            })
            .catch(function (error) {
                console.log(error);
                setErrorMsg(error.response.data.message);
                setShowError(true);
            });
    }

    const handleSubmit = (event) => {
        const testResult = {
            "fullName": fullName,
            "email": email,
            "age": age,
            "gender": gender,
            "address": address,
            "postCode": postCode,
            "ttn": ttn,
            "testResult": result
        }
        onCreate(testResult);
        event.preventDefault();
    }

    const handleClose = () => {setShowError(false)}
    const handleSuccessClose = () => {setShowSuccess(false)}

    return (
        <div className="main-container">
            <Container fluid>
                <Modal show={showError} onHide={handleClose} centered>
                    <Modal.Header>
                        <Modal.Title className="error-title">Error</Modal.Title>
                    </Modal.Header>
                    <Modal.Body>{errorMsg}</Modal.Body>
                    <Modal.Footer>
                        <Button variant="secondary" onClick={handleClose}>
                            Close
                        </Button>
                    </Modal.Footer>
                </Modal>
                <Modal show={showSuccess} onHide={handleClose} centered>
                    <Modal.Header>
                        <Modal.Title className="success-title">Submitted</Modal.Title>
                    </Modal.Header>
                    <Modal.Body>Data Submitted Successfully</Modal.Body>
                    <Modal.Footer>
                        <Button variant="secondary" onClick={handleSuccessClose}>
                            Close
                        </Button>
                    </Modal.Footer>
                </Modal>
                <Row className="justify-content-md-center">
                    <Col/>
                    <Col md={6} lg={6} sm={10} className="white-bg add-space-top add-space-bottom round-edge">
                        <Form onSubmit={handleSubmit}>
                            <Form.Group controlId="formEmail">
                                <Form.Label>Email address</Form.Label>
                                <Form.Control type="email" placeholder="Enter email" value={email}
                                              onChange={e => setEmail(e.target.value)}/>
                            </Form.Group>
                            <Form.Group controlId="formFullname">
                                <Form.Label>Full Name</Form.Label>
                                <Form.Control type="text" placeholder="Enter Name" value={fullName}
                                              onChange={e => setFullName(e.target.value)}/>
                            </Form.Group>
                            <Form.Group controlId="formAge">
                                <Form.Label>Age</Form.Label>
                                <Form.Control type="number" value={age} onChange={e => setAge(e.target.value)}/>
                            </Form.Group>
                            <Form.Group controlId="formGender">
                                <Form.Label>Gender</Form.Label>
                                <Form.Control as="select" value={gender} onChange={e => setGender(e.target.value)}
                                              custom>
                                    <option>Male</option>
                                    <option>Female</option>
                                    <option>Other</option>
                                </Form.Control>
                            </Form.Group>
                            <Form.Group controlId="formAddress">
                                <Form.Label>Address</Form.Label>
                                <Form.Control type="text" placeholder="Enter Address" value={address} onChange={e => setAddress(e.target.value)}/>
                            </Form.Group>
                            <Form.Group controlId="formPostCode">
                                <Form.Label>Post Code</Form.Label>
                                <Form.Control type="text" placeholder="Enter Post Code" value={postCode} onChange={e => setPostCode(e.target.value)}/>
                            </Form.Group>
                            <Form.Group controlId="formTtn">
                                <Form.Label>TTN Code</Form.Label>
                                <Form.Control type="text" placeholder="Enter TTN Code" value={ttn} onChange={e => setTtn(e.target.value)}/>
                            </Form.Group>
                            <Form.Group controlId="formResult">
                                <Form.Label>Test Result</Form.Label>
                                <Form.Control as="select" value={result} onChange={e => setResult(e.target.value)}
                                              custom>
                                    <option>Positive</option>
                                    <option>Negative</option>
                                    <option>Inconclusive</option>
                                </Form.Control>
                            </Form.Group>
                            <Button variant="primary" type="submit">
                                Submit
                            </Button>
                        </Form>
                    </Col>
                    <Col/>
                </Row>
            </Container>
        </div>

    )
}
