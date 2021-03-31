import {HashRouter, Link, Redirect, Route, Switch} from "react-router-dom";
import {SubmitTestResults} from "./submission/SubmitTestResults";
import 'bootstrap/dist/css/bootstrap.min.css';
import {Admin} from "./admin/admin";
import {LoginPage} from "./login/loginPage";
import {useHistory} from "react-router";
import {useEffect, useState} from "react";

const React = require('react');

export function App(props) {
    let history = useHistory();
    const [isLoggedIn, setLoggedIn] = useState(false);

    useEffect(() => {
        setLoggedIn(authStatus.isAuthenticated);
    }, [])

    const logout = () => {
        authStatus.signOut();
        setLoggedIn(false);
    };

    return (
        <HashRouter>
            <div>
                <nav>
                    <ul className="nav">
                        <li className="nav-item">
                            <Link className="nav-link active" to="/">Submit</Link>
                        </li>
                        <li className="nav-item">
                            <Link className="nav-link active" to="/admin">Admin</Link>
                        </li>
                        {isLoggedIn &&
                        <li className="nav-link">
                            <Link onClick={logout.bind(this)} to="/">Logout</Link>
                        </li>}
                    </ul>
                </nav>
                <Switch>
                    <Route path="/login">
                        <LoginPage authStatus={authStatus} setLoggedIn={setLoggedIn}/>
                    </Route>
                    <PrivateRoute path="/admin">
                        <Admin logout={logout}/>
                    </PrivateRoute>
                    <Route path="/">
                        <SubmitTestResults/>
                    </Route>
                </Switch>
            </div>
        </HashRouter>
    )
}

const authStatus = {
    isAuthenticated: function () {
        const authStateInLocalStorage = localStorage.getItem('isAuthenticated');
        return authStateInLocalStorage === 'true';
    },
    authenticate(cb) {
        localStorage.setItem('isAuthenticated', 'true');
        cb();
    },
    signOut() {
        localStorage.setItem('isAuthenticated', 'false');
    }
};

function PrivateRoute({children, ...rest}) {
    return (
        <Route
            {...rest}
            render={({location}) =>
                authStatus.isAuthenticated() ? (
                    children
                ) : (
                    <Redirect
                        to={{
                            pathname: "/login",
                            state: {from: location}
                        }}
                    />
                )
            }
        />
    );
}
