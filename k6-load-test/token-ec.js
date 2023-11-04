import http from 'k6/http'

export const options = {
    stages: [
      { duration: '1m', target: 1000 },
      { duration: '2m', target: 1000 },
      { duration: '1m', target: 0 }
    ]
}

export default function () {
    const url = `http://localhost:8080/token/ec`

    const tokenResponse =  http.post(url)
}
