import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { Compte_bancaire } from 'app/shared/model/compte-bancaire.model';
import { Compte_bancaireService } from './compte-bancaire.service';
import { Compte_bancaireComponent } from './compte-bancaire.component';
import { Compte_bancaireDetailComponent } from './compte-bancaire-detail.component';
import { Compte_bancaireUpdateComponent } from './compte-bancaire-update.component';
import { Compte_bancaireDeletePopupComponent } from './compte-bancaire-delete-dialog.component';
import { ICompte_bancaire } from 'app/shared/model/compte-bancaire.model';

@Injectable({ providedIn: 'root' })
export class Compte_bancaireResolve implements Resolve<ICompte_bancaire> {
    constructor(private service: Compte_bancaireService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<ICompte_bancaire> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<Compte_bancaire>) => response.ok),
                map((compte_bancaire: HttpResponse<Compte_bancaire>) => compte_bancaire.body)
            );
        }
        return of(new Compte_bancaire());
    }
}

export const compte_bancaireRoute: Routes = [
    {
        path: '',
        component: Compte_bancaireComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterApp.compte_bancaire.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/view',
        component: Compte_bancaireDetailComponent,
        resolve: {
            compte_bancaire: Compte_bancaireResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterApp.compte_bancaire.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'new',
        component: Compte_bancaireUpdateComponent,
        resolve: {
            compte_bancaire: Compte_bancaireResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterApp.compte_bancaire.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/edit',
        component: Compte_bancaireUpdateComponent,
        resolve: {
            compte_bancaire: Compte_bancaireResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterApp.compte_bancaire.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const compte_bancairePopupRoute: Routes = [
    {
        path: ':id/delete',
        component: Compte_bancaireDeletePopupComponent,
        resolve: {
            compte_bancaire: Compte_bancaireResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterApp.compte_bancaire.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
