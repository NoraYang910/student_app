
import VueRouter from 'vue-router';

const routes = [
    {
        path:'/',
        name:'login',
        component:()=>import('../components/LoginPage.vue')
    },
    {
        path:'/Index',
        name:'index',
        component:()=>import('../components/IndexPage.vue'),
        children:[
            {
                path:'/',
                name:'HomePage',
                component:()=>import('../components/HomePage.vue')
            },
            {
                path:'/HomePage',
                name:'HomePage',
                component:()=>import('../components/HomePage.vue')
            },
            {
                path:'/CourseManage',
                name:'CourseManage',
                component:()=>import('../components/CourseManage.vue')
            },
            {
                path:'/MessageBox',
                name:'MessageBox',
                component:()=>import('../components/MessageBox.vue')
            }
        ]
    }
]

const router = new VueRouter({
    mode:'history',
    routes
})

export function resetRouter() {
    router.matcher = new VueRouter({
        mode:'history',
        routes: []
    }).matcher
}
export  default router;