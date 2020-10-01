import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { Vehicule } from 'app/shared/model/vehicule.model';
import { VehiculeService } from './vehicule.service';
import { VehiculeComponent } from './vehicule.component';
import { VehiculeDetailComponent } from './vehicule-detail.component';
import { VehiculeUpdateComponent } from './vehicule-update.component';
import { VehiculeDeletePopupComponent } from './vehicule-delete-dialog.component';
import { IVehicule } from 'app/shared/model/vehicule.model';

@Injectable({ providedIn: 'root' })
export class VehiculeResolve implements Resolve<IVehicule> {
    constructor(private service: VehiculeService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IVehicule> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<Vehicule>) => response.ok),
                map((vehicule: HttpResponse<Vehicule>) => vehicule.body)
            );
        }
        return of(new Vehicule());
    }
}

export const vehiculeRoute: Routes = [
    {
        path: '',
        component: VehiculeComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterApp.vehicule.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/view',
        component: VehiculeDetailComponent,
        resolve: {
            vehicule: VehiculeResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterApp.vehicule.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'new',
        component: VehiculeUpdateComponent,
        resolve: {
            vehicule: VehiculeResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterApp.vehicule.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/edit',
        component: VehiculeUpdateComponent,
        resolve: {
            vehicule: VehiculeResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterApp.vehicule.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const vehiculePopupRoute: Routes = [
    {
        path: ':id/delete',
        component: VehiculeDeletePopupComponent,
        resolve: {
            vehicule: VehiculeResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterApp.vehicule.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
