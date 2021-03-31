'use strict';

import {App} from "./app";
import {HashRouter} from "react-router-dom";

const React = require('react');
const ReactDOM = require('react-dom');

ReactDOM.render(
	<HashRouter>
		<App/>
	</HashRouter>,
	document.getElementById('react')
)
