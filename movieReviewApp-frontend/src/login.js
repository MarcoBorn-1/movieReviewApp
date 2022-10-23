//import logo from './logo.svg';
import React, { Component, useEffect, useState } from 'react'
import { BrowserRouter as Router, Switch, Route, Link, Outlet, Redirect } from 'react-router-dom'
import { UserOutlined } from '@ant-design/icons'
import { Input, Space } from 'antd'
import { EyeInvisibleOutlined, EyeTwoTone } from '@ant-design/icons'
import { Row, Col } from 'antd'
import { Button } from 'antd'
import { Layout } from 'antd'
import { Typography } from 'antd'
import { Form } from 'antd'
import Cookies from 'universal-cookie'

const { Header, Footer, Sider, Content } = Layout
const { Title, Text } = Typography

function timeout(delay) {
  return new Promise((res) => setTimeout(res, delay))
}

async function changeWindow() {
  await timeout(500)
  var cookie = new Cookies()
  if (cookie.get('jwt-token')) {
    window.location.reload()
  }
}

class Login extends Component {
  constructor(props) {
    super(props)
    var cookie = new Cookies()
    if (cookie.get('jwt-token')) {
      window.location.href = '/'
    }
    this.state = {
      email: '',
      password: '',
      errors: {},
      response: '',
    }
  }

  handleInputChange = (event) => {
    const target = event.target
    const value = target.type === 'checkbox' ? target.checked : target.value
    const name = target.name
    this.setState({
      [name]: value,
    })
  }

  loginUser(email, password) {
    fetch('http://localhost:8095/api/auth/login', {
      method: 'POST',
      headers: {
        Accept: 'application/json',
        'Content-Type': 'application/json',
      },
      body: JSON.stringify({
        email: email,
        password: password,
      }),
    }).then(function (response) {
      if (response.status === 200) {
        response.text().then((value) => {
          const cookies = new Cookies()
          cookies.set('jwt-token', value, { path: '/' })
          cookies.set('response', response.status, { path: '/login' })
        })
      } else if (response.status === 401) {
        console.log('Błędne dane.')
      } else {
        console.log('Coś poszło nie tak...')
      }
    })
  }

  handleSubmit = (event) => {
    this.loginUser(this.state.email, this.state.password)
    changeWindow()
  }

  render() {
    return (
      <Layout>
        <Header style={{ backgroundColor: '#1a1325', borderBottom: '2px solid #301c4d', height: '7vh' }}>
          <Row>
            <Col span={3}>
              <Link to="/">
                <Title type="danger" style={{ margin: '5px 0px', color: '#d89614' }}>
                  Curtain
                </Title>
              </Link>
            </Col>
            <Col span={2}>
              <Link to="/">
                <Title type="secondary" level={3} style={{ margin: '12px 0px', color: '#d2e0fa' }}>
                  Home
                </Title>
              </Link>
            </Col>
            <Col span={2}>
              <Link to="/movies">
                <Title type="secondary" level={3} style={{ margin: '12px 0px', color: '#d2e0fa' }}>
                  Movies
                </Title>
              </Link>
            </Col>
            <Col
              span={7}
              offset={10}
              style={{ display: 'flex', alignItems: 'end', justifyContent: 'flex-end', flexDirection: 'row' }}
            >
              <Space>
                <Link to="/login">
                  <Button
                    type="secondary"
                    size="middle"
                    style={{ backgroundColor: '#164c7e', color: 'white', border: '1px solid #111d2c' }}
                  >
                    Login
                  </Button>
                </Link>
                <Link to="/register">
                  <Button
                    type="secondary"
                    size="middle"
                    style={{ backgroundColor: '#164c7e', color: 'white', border: '1px solid #111d2c' }}
                  >
                    Register
                  </Button>
                </Link>
              </Space>
            </Col>
          </Row>
        </Header>
        <Content
          style={{
            backgroundColor: '#131629',
            display: 'flex',
            alignItems: 'center',
            justifyContent: 'center',
            flexDirection: 'column',
            height: '85vh',
          }}
        >
          <div
            className="wrapper-window"
            style={{
              backgroundColor: '#40162f',
              padding: '30px',
              borderRadius: '20px',
              display: 'flex',
              flexDirection: 'column',
              width: '400px',
              border: '1px solid white',
            }}
          >
            <Title level={1} style={{ color: '#FFFFFF', textAlign: 'center' }}>
              Login
            </Title>
            <Form name="basic" initialValues={{ remember: true }} autoComplete="off" layout="horizontal">
              <Space direction="vertical" style={{ display: 'flex', paddingTop: '15px' }}>
                <Form.Item name="email" rules={[{ required: true, message: 'Please input your email!' }]}>
                  <Input
                    name="email"
                    onChange={this.handleInputChange}
                    value={this.state.emailAddress}
                    placeholder="E-Mail"
                    size="large"
                    prefix={<UserOutlined />}
                  />
                </Form.Item>
                <Form.Item
                  value={this.state.password}
                  name="password"
                  rules={[{ required: true, message: 'Please input your password!' }]}
                >
                  <Input.Password
                    name="password"
                    onChange={this.handleInputChange}
                    placeholder="Password"
                    size="large"
                    iconRender={(visible) => (visible ? <EyeTwoTone /> : <EyeInvisibleOutlined />)}
                  ></Input.Password>
                </Form.Item>
                <Button
                  onClick={this.handleSubmit}
                  type="primary"
                  size="large"
                  block
                  style={{ backgroundColor: '#144848' }}
                >
                  Sign in
                </Button>
              </Space>
            </Form>
          </div>
        </Content>
        <Footer style={{ backgroundColor: '#1a1325', borderTop: '2px solid #301c4d', height: '8vh' }}>
          <Row justify="end">
            <Text style={{ color: '#13c2c2' }}>© 2022 - Created by Marco Born</Text>
          </Row>
        </Footer>
      </Layout>
    )
  }
  //}
}

export default Login
