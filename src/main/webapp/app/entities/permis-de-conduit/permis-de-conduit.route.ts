import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { Permis_de_conduit } from 'app/shared/model/permis-de-conduit.model';
import { Permis_de_conduitService } from './permis-de-conduit.service';
import { Permis_de_conduitComponent } from './permis-de-conduit.component';
import { Permis_de_conduitDetailComponent } from './permis-de-conduit-detail.component';
import { Permis_de_conduitUpdateComponent } from './permis-de-conduit-update.component';
import { Permis_de_conduitDeletePopupComponent } from './permis-de-conduit-delete-dialog.component';
import { IPermis_de_conduit } from 'app/shared/model/permis-de-conduit.model';

@Injectable({ providedIn: 'root' })
export class Permis_de_conduitResolve implements Resolve<IPermis_de_conduit> {
    constructor(private service: Permis_de_conduitService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IPermis_de_conduit> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<Permis_de_conduit>) => response.ok),
                map((permis_de_conduit: HttpResponse<Permis_de_conduit>) => permis_de_conduit.body)
            );
        }
        return of(new Permis_de_conduit());
    }
}

export const permis_de_conduitRoute: Routes = [
    {
        path: '',
        component: Permis_de_conduitComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterApp.permis_de_conduit.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/view',
        component: Permis_de_conduitDetailComponent,
        resolve: {
            permis_de_conduit: Permis_de_conduitResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterApp.permis_de_conduit.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'new',
        component: Permis_de_conduitUpdateComponent,
        resolve: {
            permis_de_conduit: Permis_de_conduitResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterApp.permis_de_conduit.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/edit',
        component: Permis_de_conduitUpdateComponent,
        resolve: {
            permis_de_conduit: Permis_de_conduitResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterApp.permis_de_conduit.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const permis_de_conduitPopupRoute: Routes = [
    {
        path: ':id/delete',
        component: Permis_de_conduitDeletePopupComponent,
        resolve: {
            permis_de_conduit: Permis_de_conduitResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterApp.permis_de_conduit.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
