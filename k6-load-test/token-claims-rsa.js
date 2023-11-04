import http from 'k6/http'

export const options = {
    stages: [
      { duration: '1m', target: 1000 },
      { duration: '2m', target: 1000 },
      { duration: '1m', target: 0 }
    ]
}

export function setup() {
    const url = `http://localhost:8080/token/rsa`
    const token =  http.post(url).body

    return { data: token }
}
    
export default function (data) {
    const url = `http://localhost:8080/token/rsa/claims`
    const token = data.data
    const claims = http.post(url, token)
}
