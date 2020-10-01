import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { COMMANDE_PRODUIT } from 'app/shared/model/commande-produit.model';
import { COMMANDE_PRODUITService } from './commande-produit.service';
import { COMMANDE_PRODUITComponent } from './commande-produit.component';
import { COMMANDE_PRODUITDetailComponent } from './commande-produit-detail.component';
import { COMMANDE_PRODUITUpdateComponent } from './commande-produit-update.component';
import { COMMANDE_PRODUITDeletePopupComponent } from './commande-produit-delete-dialog.component';
import { ICOMMANDE_PRODUIT } from 'app/shared/model/commande-produit.model';

@Injectable({ providedIn: 'root' })
export class COMMANDE_PRODUITResolve implements Resolve<ICOMMANDE_PRODUIT> {
    constructor(private service: COMMANDE_PRODUITService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<ICOMMANDE_PRODUIT> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<COMMANDE_PRODUIT>) => response.ok),
                map((cOMMANDE_PRODUIT: HttpResponse<COMMANDE_PRODUIT>) => cOMMANDE_PRODUIT.body)
            );
        }
        return of(new COMMANDE_PRODUIT());
    }
}

export const cOMMANDE_PRODUITRoute: Routes = [
    {
        path: '',
        component: COMMANDE_PRODUITComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterApp.cOMMANDE_PRODUIT.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/view',
        component: COMMANDE_PRODUITDetailComponent,
        resolve: {
            cOMMANDE_PRODUIT: COMMANDE_PRODUITResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterApp.cOMMANDE_PRODUIT.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'new',
        component: COMMANDE_PRODUITUpdateComponent,
        resolve: {
            cOMMANDE_PRODUIT: COMMANDE_PRODUITResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterApp.cOMMANDE_PRODUIT.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/edit',
        component: COMMANDE_PRODUITUpdateComponent,
        resolve: {
            cOMMANDE_PRODUIT: COMMANDE_PRODUITResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterApp.cOMMANDE_PRODUIT.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const cOMMANDE_PRODUITPopupRoute: Routes = [
    {
        path: ':id/delete',
        component: COMMANDE_PRODUITDeletePopupComponent,
        resolve: {
            cOMMANDE_PRODUIT: COMMANDE_PRODUITResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterApp.cOMMANDE_PRODUIT.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
