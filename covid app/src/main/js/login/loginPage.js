import {useHistory, useLocation} from "react-router-dom";
import {Button, Col, Container, Form, Modal, Row} from 'react-bootstrap';
import {useEffect, useState} from "react";
import '../mainStyle.css';
import Cookies from "universal-cookie/lib";

const axios = require('axios');
const cookies = new Cookies();

const React = require('react');

export function LoginPage(props) {

    let history = useHistory();
    let location = useLocation();

    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const [showError, setShowError] = useState(false);

    useEffect(() => {
        const unCookie = cookies.get('un');
        if (unCookie) {
            setUsername(unCookie);
        }
    }, []);

    const handleClose = () => {
        setShowError(false)
    }

    const handleSubmit = e => {
        e.preventDefault();

        const bodyFormData = new FormData();
        bodyFormData.set('username', username);
        bodyFormData.set('password', password);


        axios.post('/login', bodyFormData, {headers: {'Content-Type': 'multipart/form-data'}, withCredentials: true})
            .then(response => {
                let {from} = location.state || {from: {pathname: "/"}};
                props.setLoggedIn(true);
                cookies.set('un', username);
                props.authStatus.authenticate(() => {
                    history.push(from);
                });
            })
            .catch(err => {
                setShowError(true);
            });
    };

    return (
        <div className="main-container">
            <Modal show={showError} onHide={handleClose}>
                <Modal.Header>
                    <Modal.Title className="error-title">Error Login</Modal.Title>
                </Modal.Header>
                <Modal.Body>Invalid Username or Password</Modal.Body>
                <Modal.Footer>
                    <Button variant="secondary" onClick={handleClose}>
                        Close
                    </Button>
                </Modal.Footer>
            </Modal>
            <Container fluid>
                <Row className="justify-content-md-center">
                    <Col/>
                    <Col md={6} lg={6} sm={10} className="white-bg add-space-top add-space-bottom round-edge">
                        <Form onSubmit={handleSubmit} className="login-form">
                            <Form.Group controlId="username">
                                <Form.Label>Username</Form.Label>
                                <Form.Control type="text" value={username}
                                              onChange={e => setUsername(e.target.value)}/>
                            </Form.Group>
                            <Form.Group controlId="password">
                                <Form.Label>Password</Form.Label>
                                <Form.Control type="password" value={password}
                                              onChange={e => setPassword(e.target.value)}/>
                            </Form.Group>
                            <Button variant="primary" type="submit">
                                Log In
                            </Button>
                        </Form>
                    </Col>
                    <Col/>
                </Row>
            </Container>
        </div>
    );
}