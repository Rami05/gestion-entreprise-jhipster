import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ICOMMANDE_PRODUIT } from 'app/shared/model/commande-produit.model';

type EntityResponseType = HttpResponse<ICOMMANDE_PRODUIT>;
type EntityArrayResponseType = HttpResponse<ICOMMANDE_PRODUIT[]>;

@Injectable({ providedIn: 'root' })
export class COMMANDE_PRODUITService {
    public resourceUrl = SERVER_API_URL + 'api/commande-produits';

    constructor(protected http: HttpClient) {}

    create(cOMMANDE_PRODUIT: ICOMMANDE_PRODUIT): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(cOMMANDE_PRODUIT);
        return this.http
            .post<ICOMMANDE_PRODUIT>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(cOMMANDE_PRODUIT: ICOMMANDE_PRODUIT): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(cOMMANDE_PRODUIT);
        return this.http
            .put<ICOMMANDE_PRODUIT>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<ICOMMANDE_PRODUIT>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<ICOMMANDE_PRODUIT[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    protected convertDateFromClient(cOMMANDE_PRODUIT: ICOMMANDE_PRODUIT): ICOMMANDE_PRODUIT {
        const copy: ICOMMANDE_PRODUIT = Object.assign({}, cOMMANDE_PRODUIT, {
            dateCreation:
                cOMMANDE_PRODUIT.dateCreation != null && cOMMANDE_PRODUIT.dateCreation.isValid()
                    ? cOMMANDE_PRODUIT.dateCreation.format(DATE_FORMAT)
                    : null,
            dateModification:
                cOMMANDE_PRODUIT.dateModification != null && cOMMANDE_PRODUIT.dateModification.isValid()
                    ? cOMMANDE_PRODUIT.dateModification.toJSON()
                    : null
        });
        return copy;
    }

    protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
        if (res.body) {
            res.body.dateCreation = res.body.dateCreation != null ? moment(res.body.dateCreation) : null;
            res.body.dateModification = res.body.dateModification != null ? moment(res.body.dateModification) : null;
        }
        return res;
    }

    protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        if (res.body) {
            res.body.forEach((cOMMANDE_PRODUIT: ICOMMANDE_PRODUIT) => {
                cOMMANDE_PRODUIT.dateCreation = cOMMANDE_PRODUIT.dateCreation != null ? moment(cOMMANDE_PRODUIT.dateCreation) : null;
                cOMMANDE_PRODUIT.dateModification =
                    cOMMANDE_PRODUIT.dateModification != null ? moment(cOMMANDE_PRODUIT.dateModification) : null;
            });
        }
        return res;
    }
}
