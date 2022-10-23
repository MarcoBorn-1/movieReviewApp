import React from 'react'
import { useEffect, useRef } from 'react'
import * as Typed from 'typed.js'
import { Link } from 'react-router-dom'
import { UserOutlined } from '@ant-design/icons'
import { Input, Space } from 'antd'
import { EyeInvisibleOutlined, EyeTwoTone } from '@ant-design/icons'
import { Row, Col } from 'antd'
import { Button } from 'antd'
import { Layout } from 'antd'
import { Typography } from 'antd'
import { Divider } from 'antd'
import Cookies from 'universal-cookie'
import jwt_decode from 'jwt-decode'

const { Header, Footer, Sider, Content } = Layout
const { Title, Text } = Typography

function logout() {
  var cookie = new Cookies()
  cookie.remove('jwt-token')
  window.location.href = '/'
  window.location.reload()
}

function App() {
  const typedJSRef = useRef(null)
  var login
  var cookie = new Cookies()

  // Setting up typedJS
  useEffect(() => {
    const typedJS = new Typed(typedJSRef.current, {
      strings: ['movie', 'cinema', 'viewing', 'reviewing'],
      typeSpeed: 110,
      backSpeed: 110,
      backDelay: 500,
      startDelay: 500,
      loop: true,
    })

    return () => typedJS.destroy()
  }, [])

  var admin_movie
  var admin_users
  var user_id
  var user_name
  var is_admin

  if (cookie.get('jwt-token')) {
    var token_info = jwt_decode(cookie.get('jwt-token'))
    user_name = token_info['name']
    is_admin = token_info['isAdmin']
    user_id = token_info['userID']

    if (is_admin == "true") {
      console.log("Welcome ADMIN")
      admin_movie = (
        <Link to="/adminMovies">
          <Title type="secondary" level={3} style={{ margin: '12px 0px', color: '#d2e0fa' }}>
            Admin - Movies
          </Title>
        </Link>
      )
      admin_users = (
        <Link to="/adminUsers">
          <Title type="secondary" level={3} style={{ margin: '12px 0px', color: '#d2e0fa' }}>
            Admin - Users
          </Title>
        </Link>
      )
    }

    login = (
      <Space size="large">
        <Title level={5} style={{ color: 'white' }}>
          Welcome back {user_name}!
        </Title>
        <Button
          type="secondary"
          size="middle"
          style={{ backgroundColor: '#164c7e', color: 'white', border: '1px solid #111d2c' }}
          onClick={logout}
        >
          Logout
        </Button>
      </Space>
    )
  } else {
    login = (
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
        <Button
          type="secondary"
          size="middle"
          style={{ backgroundColor: '#164c7e', color: 'white', border: '1px solid #111d2c' }}
          href="/register"
        >
          Register
        </Button>
      </Space>
    )
  }

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
          <Col span={3}>
            {admin_movie}
          </Col>
          <Col span={3}>
            {admin_users}
          </Col>
          <Col
            span={7}
            offset={4}
            style={{
              display: 'flex',
              alignItems: 'end',
              justifyContent: 'flex-end',
              flexDirection: 'row',
            }}
          >
            {login}
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
        <Title type="danger" style={{ margin: '5px 0px', color: '#d89614', fontSize: '69px' }}>
          Curtain
        </Title>

        <Title type="primary" style={{ margin: '5px 0px', color: '#FAFAFA' }}>
          The site for all of your <span ref={typedJSRef} /> needs.
        </Title>
      </Content>
      <Footer style={{ backgroundColor: '#1a1325', borderTop: '2px solid #301c4d', height: '8vh' }}>
        <Row justify="end">
          <Text style={{ color: '#13c2c2' }}>© 2022 - Created by Marco Born</Text>
        </Row>
      </Footer>
    </Layout>
  )
}

export default App
