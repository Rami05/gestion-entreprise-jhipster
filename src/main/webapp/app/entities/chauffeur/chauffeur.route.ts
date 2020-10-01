import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { Chauffeur } from 'app/shared/model/chauffeur.model';
import { ChauffeurService } from './chauffeur.service';
import { ChauffeurComponent } from './chauffeur.component';
import { ChauffeurDetailComponent } from './chauffeur-detail.component';
import { ChauffeurUpdateComponent } from './chauffeur-update.component';
import { ChauffeurDeletePopupComponent } from './chauffeur-delete-dialog.component';
import { IChauffeur } from 'app/shared/model/chauffeur.model';

@Injectable({ providedIn: 'root' })
export class ChauffeurResolve implements Resolve<IChauffeur> {
    constructor(private service: ChauffeurService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IChauffeur> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<Chauffeur>) => response.ok),
                map((chauffeur: HttpResponse<Chauffeur>) => chauffeur.body)
            );
        }
        return of(new Chauffeur());
    }
}

export const chauffeurRoute: Routes = [
    {
        path: '',
        component: ChauffeurComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterApp.chauffeur.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/view',
        component: ChauffeurDetailComponent,
        resolve: {
            chauffeur: ChauffeurResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterApp.chauffeur.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'new',
        component: ChauffeurUpdateComponent,
        resolve: {
            chauffeur: ChauffeurResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterApp.chauffeur.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/edit',
        component: ChauffeurUpdateComponent,
        resolve: {
            chauffeur: ChauffeurResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterApp.chauffeur.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const chauffeurPopupRoute: Routes = [
    {
        path: ':id/delete',
        component: ChauffeurDeletePopupComponent,
        resolve: {
            chauffeur: ChauffeurResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterApp.chauffeur.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
