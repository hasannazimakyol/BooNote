import http from "@/lib/http";

export function signUp(body) {
    return http.post('api/v1/users', body);
}

// export function signUp(body) {
//     return http.post('api/v1/users', body, {
//         headers: {
//             "Accept-Language": i18nInstance.language
//         }
//     });
// }